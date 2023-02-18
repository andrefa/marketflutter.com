package com.market.flutter.api.services.trader.strategies;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.market.flutter.api.models.domain.Asset;
import com.market.flutter.api.models.domain.AssetConfig;
import com.market.flutter.api.models.domain.AssetTransaction;
import com.market.flutter.api.models.domain.TransactionType;
import com.market.flutter.api.repositories.AssetRepository;
import com.market.flutter.api.services.communications.NotificationService;
import com.market.flutter.api.services.trader.actions.ExecuteBuyAction;
import com.market.flutter.api.services.trader.actions.ExecuteSellAction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultTradeStrategy implements TradeStrategyExecutor {

    @Value("${binance.fee.sell}")
    private final BigDecimal sellPercentageFee;

    private final ExecuteBuyAction executeBuyAction;
    private final ExecuteSellAction executeSellAction;
    private final AssetRepository assetRepository;
    private final NotificationService notificationService;

    @Override
    public void execute(Asset asset, BigDecimal newPrice) {
        log.info("Executing default strategy for asset {} and last seen price {}", asset.getId(), newPrice);

        AssetConfig config = asset.getAssetConfig();

        processBuy(asset, newPrice, config);
        processSell(asset, newPrice, config);

        config.setLastSeenPrice(newPrice);
        assetRepository.save(asset);
    }

    private void processBuy(Asset asset, BigDecimal newPrice, AssetConfig config) {
        if (shouldBuy(asset, config, newPrice)) {
            BigDecimal amountToBuy = config.getBuyingAmount();
            Optional<AssetTransaction> transaction = executeBuyAction.execute(asset, amountToBuy, newPrice);

            transaction.ifPresent(tx -> {
                String message = String.format("User %s bought %s %s for %s %s.", asset.getUser().getId(), tx.getAmount(), tx.getCoinIn().getCryptoCode(), tx.getPurchasePrice(), tx.getCoinOut().getCryptoCode());
                log.info(message);
                notificationService.notifyUser(asset.getUser(), message);
            });
        }
    }

    private void processSell(Asset asset, BigDecimal newPrice, AssetConfig config) {
        for (AssetTransaction buyTransaction : asset.getBuyTransactions()) {
            BigDecimal sellThreshold = config.getThresholdForSelling().add(sellPercentageFee);

            if (shouldSell(newPrice, buyTransaction, sellThreshold)) {
                Optional<AssetTransaction> transaction = executeSellAction.execute(buyTransaction, newPrice);

                transaction.ifPresent(tx -> {
                    String message = String.format("User %s sold %s %s for %s %s.", asset.getUser().getId(), tx.getAmount(), tx.getCoinIn().getCryptoCode(), tx.getPurchasePrice(), tx.getCoinOut().getCryptoCode());
                    log.info(message);
                    notificationService.notifyUser(asset.getUser(), message);
                });
            }
        }
    }

    private boolean shouldBuy(Asset asset, AssetConfig config, BigDecimal newPrice) {
        BigDecimal buyThreshold = config.getThresholdForBuying();
        List<AssetTransaction> transactions = asset.getAssetTransactions();
        Integer maxHoldingTransactions = config.getMaxHoldingTransactions();

        boolean holdsLessThanLimit = transactions.size() < maxHoldingTransactions
                || transactions.stream()
                        .sorted(Comparator.comparing(AssetTransaction::getCreated).reversed())
                        .limit(maxHoldingTransactions)
                        .anyMatch(tx -> tx.getTransactionType() == TransactionType.SELL);

        BigDecimal consideredBasePrice = transactions.stream()
                .filter(tx -> tx.getTransactionType() == TransactionType.SELL)
                .max(Comparator.comparing(AssetTransaction::getCreated))
                .map(AssetTransaction::getPurchasePrice)
                .orElse(config.getBasePrice());

        boolean priceAllowedForBuying = newPrice.compareTo(consideredBasePrice.multiply(BigDecimal.ONE.subtract(buyThreshold))) < 0;

        return holdsLessThanLimit && priceAllowedForBuying;
    }

    private static boolean shouldSell(BigDecimal newPrice, AssetTransaction buyTransaction, BigDecimal sellThreshold) {
        BigDecimal lastSeenPrice = buyTransaction.getAsset().getAssetConfig().getLastSeenPrice();
        BigDecimal purchasePrice = buyTransaction.getPurchasePrice();

        // If newPrice is above or equals the sell threshold and the new price is below the last seen price, sell
        return newPrice.compareTo(purchasePrice.multiply(BigDecimal.ONE.add(sellThreshold))) >= 0
                && (lastSeenPrice == null || newPrice.compareTo(lastSeenPrice) < 0);
    }

}
