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
public class ExecuteBuyAction extends ExecuteTransactionAction {

    public ExecuteBuyAction(BinanceClientService binanceClientService, AssetTransactionRepository assetTransactionRepository) {
        super(binanceClientService, assetTransactionRepository);
    }

    public Optional<AssetTransaction> execute(Asset asset, BigDecimal amount, BigDecimal price) {
        if (!asset.getAssetConfig().isBuyEnabled()) {
            log.info("Buy is disabled for asset {}", asset.getId());
            return Optional.empty();
        }

        Coin coinIn = asset.getCoin();
        Coin coinOut = asset.getAssetConfig().getExchangeCoin();

        log.info("Buying {} of {} for user {}", amount, coinIn, asset.getUser().getId());
        return transactCoins(asset, coinIn, coinOut, amount, price);
    }

}
