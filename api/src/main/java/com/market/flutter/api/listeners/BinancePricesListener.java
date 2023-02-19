package com.market.flutter.api.listeners;


import java.util.List;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.market.flutter.api.models.domain.Asset;
import com.market.flutter.api.models.domain.Coin;
import com.market.flutter.api.repositories.AssetRepository;
import com.market.flutter.api.services.trader.strategies.TradeStrategyFactory;

import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.core.ProductSubscription.ProductSubscriptionBuilder;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingMarketDataService;
import io.reactivex.disposables.Disposable;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(Ordered.LOWEST_PRECEDENCE)
public class BinancePricesListener {

    @Value("${binance.default.coin}")
    private final String defaultTradedCoin;

    @Qualifier("binanceStreamingExchange")
    private final StreamingExchange streamingExchange;
    private final TradeStrategyFactory tradeStrategyFactory;
    private final AssetRepository assetRepository;

    private List<Disposable> websocketConnections;

    @PostConstruct
    public void init() {
        List<Asset> assets = assetRepository.findAll();

        ProductSubscriptionBuilder subscriptionBuilder = ProductSubscription.create();

        assets.forEach(asset -> {
            String coinCode = asset.getCoin().getCryptoCode();
            String exchangeCode = asset.getAssetConfig().getExchangeCoin().getCryptoCode();
            CurrencyPair currencyPair = new CurrencyPair(coinCode, exchangeCode);
            subscriptionBuilder.addTicker(currencyPair);
        });

        streamingExchange.connect(subscriptionBuilder.build()).blockingAwait();
        StreamingMarketDataService streamingMarketDataService = streamingExchange.getStreamingMarketDataService();

        websocketConnections = assets.stream().map(asset -> {
            String coinCode = asset.getCoin().getCryptoCode();
            String exchangeCode = asset.getAssetConfig().getExchangeCoin().getCryptoCode();
            CurrencyPair currencyPair = new CurrencyPair(coinCode, exchangeCode);
            return streamingMarketDataService.getTicker(currencyPair)
                    .subscribe(ticker -> onReceive(asset.getCoin(), ticker));
        }).toList();
    }

    @PreDestroy
    public void destroy() {
        log.info("Closing all websocket connections.");
        websocketConnections.forEach(Disposable::dispose);
    }

    private void onReceive(Coin coin, Ticker ticker) {
        log.info("Tick '{}' received for coin '{}'", ticker, coin);

        assetRepository.findByCoin(coin).forEach(asset -> tradeStrategyFactory.getStrategy(asset).execute(asset, ticker.getBid()));
    }

}
