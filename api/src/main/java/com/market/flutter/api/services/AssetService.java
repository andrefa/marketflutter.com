package com.market.flutter.api.services;

import java.math.BigDecimal;
import java.util.List;

import org.knowm.xchange.binance.dto.marketdata.BinanceTicker24h;
import org.knowm.xchange.binance.service.BinanceMarketDataService;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Service;

import com.market.flutter.api.models.domain.Asset;
import com.market.flutter.api.models.domain.AssetTransaction;
import com.market.flutter.api.models.domain.TransactionType;
import com.market.flutter.api.models.dto.UserAsset;
import com.market.flutter.api.models.dto.UserAssetConfig;
import com.market.flutter.api.repositories.AssetConfigRepository;
import com.market.flutter.api.repositories.AssetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;

    private final AssetConfigRepository assetConfigRepository;

    private final BinanceMarketDataService marketDataService;

    public List<UserAsset> listAssetsPerUser(String userEmail) {
        return assetRepository.listByUserUserEmail(userEmail)
                .stream()
                .map(asset -> UserAsset.builder()
                        .coinSymbol(asset.getCoin().getCryptoCode())
                        .balance(calculateAssetBalance(asset))
                        .tradeStrategy(asset.getTradeStrategy())
                        .userAssetConfig(UserAssetConfig.builder()
                                .exchangeCoinSymbol(asset.getAssetConfig().getExchangeCoin().getCryptoCode())
                                .basePrice(asset.getAssetConfig().getBasePrice())
                                .lastSeenPrice(asset.getAssetConfig().getLastSeenPrice())
                                .thresholdForBuying(asset.getAssetConfig().getThresholdForBuying())
                                .thresholdForSelling(asset.getAssetConfig().getThresholdForSelling())
                                .buyingAmount(asset.getAssetConfig().getBuyingAmount())
                                .buyEnabled(asset.getAssetConfig().isBuyEnabled())
                                .sellEnabled(asset.getAssetConfig().isSellEnabled())
                                .maxHoldingTransactions(asset.getAssetConfig().getMaxHoldingTransactions())
                                .build())
                        .build())
                .toList();
    }

    public void resetUserAssetsBasePrice(String userEmail) {
        assetRepository.listByUserUserEmail(userEmail)
                .forEach(asset -> {
                    try {
                        String baseSymbol = asset.getCoin().getCryptoCode();
                        String counterSymbol = asset.getAssetConfig().getExchangeCoin().getCryptoCode();
                        log.info("Processing base price for asset {} with pair {}-{}", asset.getId(), baseSymbol, counterSymbol);

                        CurrencyPair currencyPair = new CurrencyPair(baseSymbol, counterSymbol);
                        BinanceTicker24h ticker = marketDataService.ticker24hAllProducts(currencyPair);
                        BigDecimal lastPrice = ticker.getLastPrice();

                        asset.getAssetConfig().setBasePrice(lastPrice);
                        asset.getAssetConfig().setLastSeenPrice(lastPrice);

                        assetConfigRepository.save(asset.getAssetConfig());
                        log.info("Updated base price for asset {} with pair {}-{} to {}", asset.getId(), baseSymbol, counterSymbol, lastPrice);
                    } catch (Exception e) {
                        log.warn("Failed to update base price for asset {} with pair {}-{}", asset.getId(), asset.getCoin().getCryptoCode(), asset.getAssetConfig().getExchangeCoin().getCryptoCode(), e);
                    }
                });
    }

    private BigDecimal calculateAssetBalance(Asset asset) {
        return asset.getAssetTransactions()
                .stream()
                .map(this::calculateTransactionBalance)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateTransactionBalance(AssetTransaction transaction) {
        return transaction.getTransactionType() == TransactionType.BUY
                ? transaction.getAmount()
                : transaction.getAmount().negate();
    }

}
