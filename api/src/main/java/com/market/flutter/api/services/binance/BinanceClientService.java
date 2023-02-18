package com.market.flutter.api.services.binance;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiFunction;

import org.springframework.stereotype.Service;

import com.binance.connector.client.SpotClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.flutter.api.models.api.binance.ExchangeInfo;
import com.market.flutter.api.models.domain.Coin;
import com.market.flutter.api.models.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceClientService {

    private final SpotClient unauthenticatedBinanceClient;

    private final BiFunction<String, String, SpotClient> authenticatedBinanceClientFactory;

    private final ObjectMapper objectMapper;

    public ExchangeInfo fetchExchangeInfo() {
        try {
            String result = unauthenticatedBinanceClient.createMarket().exchangeInfo(new LinkedHashMap<>());
            return objectMapper.readValue(result, ExchangeInfo.class);
        } catch (Exception e) {
            log.error("Failed to fetch exchange info", e);
        }

        return new ExchangeInfo(null, null, List.of(), List.of());
    }

    public void executeTrade(User user, Coin coinIn, Coin coinOut, BigDecimal amount) {
        log.info("Trading {} of {} per {} for user {}", amount, coinOut, coinIn, user.getId());

        SpotClient binanceClient = authenticatedBinanceClientFactory.apply(user.getUserConfig().getBinanceApiKey(), user.getUserConfig().getBinanceSecretKey());
        binanceClient.createTrade().newOrder(new LinkedHashMap<>());
        // TODO implement the actual trade
    }

}
