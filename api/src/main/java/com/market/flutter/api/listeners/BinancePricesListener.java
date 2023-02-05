package com.market.flutter.api.listeners;

import java.util.List;

import org.springframework.stereotype.Component;

import com.binance.connector.client.WebsocketClient;
import com.market.flutter.api.models.api.binance.ExchangeInfo;
import com.market.flutter.api.services.binance.BinanceClientService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class BinancePricesListener {

    private final WebsocketClient binanceWebsocketClient;

    // private final AssetRepository assetRepository;

    private final BinanceClientService binanceClientService;

    @PostConstruct
    public void init() {
        // List<String> tradedAssets = assetRepository.tradedAssets()
        //         .stream()
        //         .map(ass -> ass + "@trade")
        //         .toList();

        ExchangeInfo info = binanceClientService.fetchExchangeInfo();

        List<String> tradedAssets = info.symbols()
                .stream()
                .map(symbol -> symbol.symbol().toLowerCase())
                .map(symbol -> symbol + "@trade")
                .toList();

        log.info("Connecting to trade streams '{}'.", tradedAssets);
        //binanceWebsocketClient.combineStreams(new ArrayList<>(tradedAssets), this::onReceive);
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
