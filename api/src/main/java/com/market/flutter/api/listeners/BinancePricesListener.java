package com.market.flutter.api.listeners;

import java.util.List;

import org.springframework.stereotype.Component;

import com.binance.connector.client.WebsocketClient;
import com.market.flutter.api.models.api.binance.ExchangeInfo;
import com.market.flutter.api.services.binance.BinanceClientService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BinancePricesListener {

    private final WebsocketClient binanceWebsocketClient;

    private final BinanceClientService binanceClientService;

    @PostConstruct
    public void init() {
        ExchangeInfo info = binanceClientService.fetchExchangeInfo();

        List<String> tradedAssets = info.symbols()
                .stream()
                .map(symbol -> symbol.symbol().toLowerCase())
                .map(symbol -> symbol + "@trade")
                .toList();

        log.info("Connecting to trade streams '{}'.", tradedAssets);
//        binanceWebsocketClient.combineStreams(new ArrayList<>(tradedAssets), this::onReceive);
    }

    @PreDestroy
    public void destroy() {
        log.info("Closing all websocket connections.");
        binanceWebsocketClient.closeAllConnections();
    }

    private void onReceive(String data) {
        log.info("BinancePricesListener.onReceive() {}", data);
    }

}
