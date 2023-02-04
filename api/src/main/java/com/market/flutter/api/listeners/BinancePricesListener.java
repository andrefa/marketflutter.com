package com.market.flutter.api.listeners;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.binance.connector.client.WebsocketClient;
import com.market.flutter.api.repositories.AssetRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class BinancePricesListener {

    private final WebsocketClient binanceWebsocketClient;

    private final AssetRepository assetRepository;
    
    @PostConstruct
    public void init() {
        List<String> tradedAssets = assetRepository.tradedAssets()
            .stream()
            .map(ass -> ass + "@trade")
            .toList();

        log.debug("Connecting to trade streams '{}'.", tradedAssets);
        binanceWebsocketClient.combineStreams(new ArrayList<>(tradedAssets), this::onReceive);
    }

    @PreDestroy
    public void destroy() {
        log.debug("Closing all websocket connections.");
        binanceWebsocketClient.closeAllConnections();
    }

   private void onReceive(String data) {
        log.debug("BinancePricesListener.onReceive() {}", data);
   }

}
