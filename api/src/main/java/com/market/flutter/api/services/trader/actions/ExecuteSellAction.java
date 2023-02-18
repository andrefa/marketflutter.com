package com.market.flutter.api.services.trader.actions;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.market.flutter.api.models.domain.Asset;
import com.market.flutter.api.models.domain.AssetTransaction;
import com.market.flutter.api.models.domain.Coin;
import com.market.flutter.api.repositories.AssetTransactionRepository;
import com.market.flutter.api.services.binance.BinanceClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExecuteSellAction extends ExecuteTransactionAction {

    public ExecuteSellAction(BinanceClientService binanceClientService, AssetTransactionRepository assetTransactionRepository) {
        super(binanceClientService, assetTransactionRepository);
    }

    public Optional<AssetTransaction> execute(AssetTransaction buyTransaction, BigDecimal price) {
        Asset asset = buyTransaction.getAsset();
        if (!asset.getAssetConfig().isSellEnabled()) {
            log.info("Sell is disabled for asset {}", asset.getId());
            return Optional.empty();
        }

        Coin coinIn = asset.getAssetConfig().getExchangeCoin();
        Coin coinOut = asset.getCoin();
        BigDecimal amount = buyTransaction.getAmount();

        log.info("Selling {} of {} for user {}", amount, coinOut, asset.getUser().getId());
        return transactCoins(asset, coinIn, coinOut, amount, price);
    }



}
