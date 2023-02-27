package com.market.flutter.api.services.trader.actions;

import java.math.BigDecimal;
import java.util.Optional;

import com.market.flutter.api.models.domain.Asset;
import com.market.flutter.api.models.domain.AssetTransaction;
import com.market.flutter.api.models.domain.Coin;
import com.market.flutter.api.models.domain.TransactionStatus;
import com.market.flutter.api.models.domain.TransactionType;
import com.market.flutter.api.repositories.AssetTransactionRepository;
import com.market.flutter.api.services.binance.BinanceClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ExecuteTransactionAction {

    protected final BinanceClientService binanceClientService;

    protected final AssetTransactionRepository assetTransactionRepository;

    protected Optional<AssetTransaction> transactCoins(Asset asset, Coin coinIn, Coin coinOut, BigDecimal amount, BigDecimal price) {
        TransactionStatus transactionStatus = TransactionStatus.PENDING;
        if (asset.getUser().getUserConfig().isExecuteTransactionsEnabled()) {
            binanceClientService.executeTrade(asset.getUser(), coinIn, coinOut, amount);
            transactionStatus = TransactionStatus.EMULATED;
        }

        AssetTransaction assetTransaction = AssetTransaction.builder()
                .asset(asset)
                .coinIn(coinIn)
                .coinOut(coinOut)
                .transactionType(TransactionType.BUY)
                .amount(amount)
                .purchasePrice(price)
                .transactionStatus(transactionStatus)
                .build();
        assetTransactionRepository.save(assetTransaction);

        return Optional.of(assetTransaction);
    }

}
