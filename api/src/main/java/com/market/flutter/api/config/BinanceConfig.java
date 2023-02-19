package com.market.flutter.api.config;

import java.util.Optional;
import java.util.function.Function;

import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.binance.service.BinanceMarketDataService;
import org.knowm.xchange.service.trade.TradeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.market.flutter.api.models.domain.UserConfig;

import info.bitrich.xchangestream.binance.BinanceStreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class BinanceConfig {

    @Value("${binance.enable.test-mode}")
    private final boolean enableTestMode;

    @Bean
    @Qualifier("binanceTradeFactory")
    public Function<UserConfig, TradeService> binanceTradeFactory() {
        return config -> buildBinanceExchange(Optional.of(config)).getTradeService();
    }

    @Bean
    @Qualifier("binanceStreamingExchange")
    public StreamingExchange binanceStreamingExchange() {
        return StreamingExchangeFactory.INSTANCE.createExchange(BinanceStreamingExchange.class);
    }

    @Bean
    public BinanceMarketDataService binanceMarketDataService() {
        return (BinanceMarketDataService) buildBinanceExchange(Optional.empty()).getMarketDataService();
    }

    private BinanceExchange buildBinanceExchange(Optional<UserConfig> userConfig) {
        ExchangeSpecification spec = new ExchangeSpecification(BinanceExchange.class);

        if (enableTestMode) {
            spec.setExchangeSpecificParametersItem(BinanceExchange.SPECIFIC_PARAM_USE_SANDBOX, true);
        }

        userConfig.ifPresent(config -> {
            spec.setApiKey(config.getBinanceApiKey());
            spec.setSecretKey(config.getBinanceSecretKey());
        });

        return (BinanceExchange) ExchangeFactory.INSTANCE.createExchange(spec);
    }

}
