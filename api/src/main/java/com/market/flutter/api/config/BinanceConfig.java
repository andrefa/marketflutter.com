package com.market.flutter.api.config;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.WebsocketClient;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.impl.WebsocketClientImpl;

@Configuration
public class BinanceConfig {

    @Bean
    public SpotClient unauthenticatedBinanceClient(@Value("${binance.url.api}") String apiUrl) {
        return new SpotClientImpl(apiUrl);
    }

    @Bean
    public BiFunction<String, String, SpotClient> authenticatedBinanceClientFactory(@Value("${binance.url.api}") String apiUrl) {
        return (apiKey, apiSecret) -> new SpotClientImpl(apiKey, apiSecret, apiUrl);
    }

    @Bean
    public WebsocketClient binanceWebsocketClient(@Value("${binance.url.websocket}") String apiUrl) {
        return new WebsocketClientImpl(apiUrl);
    }

}
