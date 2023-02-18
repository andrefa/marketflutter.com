package com.market.flutter.api.listeners;

import static org.apache.commons.lang3.StringUtils.lowerCase;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.binance.connector.client.WebsocketClient;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.flutter.api.models.domain.Coin;
import com.market.flutter.api.models.domain.InterestLevel;
import com.market.flutter.api.repositories.AssetRepository;
import com.market.flutter.api.repositories.CoinRepository;
import com.market.flutter.api.services.trader.strategies.TradeStrategyFactory;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BinancePricesListener {

    @Value("${binance.default.coin}")
    private final String defaultTradedCoin;

    private final WebsocketClient binanceWebsocketClient;

    private final TradeStrategyFactory tradeStrategyFactory;

    private final CoinRepository coinRepository;

    private final AssetRepository assetRepository;

    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        List<Coin> hotCoins = coinRepository.findByInterestLevel(InterestLevel.HOT);
        hotCoins.forEach(coin -> {
            String code = coin.getCryptoCode();
            String tradeStream = lowerCase(code + defaultTradedCoin);
            log.info("Subscribing to trade stream '{}'.", tradeStream);
            binanceWebsocketClient.tradeStream(tradeStream, data -> onReceive(coin, data));
        });
    }

    @PreDestroy
    public void destroy() {
        log.info("Closing all websocket connections.");
        binanceWebsocketClient.closeAllConnections();
    }

    private void onReceive(Coin coin, String data) {
        try {
            log.info("Tx '{}' received for coin '{}'", data, coin);
            BinanceTransaction transaction = objectMapper.readValue(data, BinanceTransaction.class);

            assetRepository.findByCoin(coin).forEach(asset -> {
                tradeStrategyFactory.getStrategy(asset).execute(asset, new BigDecimal(transaction.price()));
            });
        } catch (JsonProcessingException e) {
            log.error("Failed to parse transaction data", e);
        }
    }

}


record BinanceTransaction(
        @JsonProperty("e") String eventType,
        @JsonProperty("E") long eventTime,
        @JsonProperty("s") String symbol,
        @JsonProperty("t") long tradeId,
        @JsonProperty("p") String price,
        @JsonProperty("q") String quantity,
        @JsonProperty("b") long buyerOrderId,
        @JsonProperty("a") long sellerOrderId,
        @JsonProperty("T") long tradeTime,
        @JsonProperty("m") boolean isBuyerMaker,
        @JsonProperty("M") boolean isBestMatch) {
}
