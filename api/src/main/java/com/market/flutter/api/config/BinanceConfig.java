package com.market.flutter.api.config;

import java.util.function.Function;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.binance.service.BinanceMarketDataService;
import org.knowm.xchange.service.trade.TradeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.market.flutter.api.models.domain.UserConfig;

import info.bitrich.xchangestream.binance.BinanceStreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;


@Configuration
public class BinanceConfig {

    @Bean
    @Qualifier("binanceTradeFactory")
    public Function<UserConfig, TradeService> binanceTradeFactory() {
        return config -> buildBinanceExchange(config).getTradeService();
    }

    @Bean
    @Qualifier("binanceStreamingExchange")
    public StreamingExchange binanceStreamingExchange() {
        return StreamingExchangeFactory.INSTANCE.createExchange(BinanceStreamingExchange.class.getName());
    }

    @Bean
    public BinanceMarketDataService binanceMarketDataService() {
        return (BinanceMarketDataService) buildBinanceExchange().getMarketDataService();
    }

    private Exchange buildBinanceExchange(UserConfig userConfig) {
        Exchange exchange = buildBinanceExchange();
        exchange.getExchangeSpecification().setApiKey(userConfig.getBinanceApiKey());
        exchange.getExchangeSpecification().setSecretKey(userConfig.getBinanceSecretKey());
        return exchange;
    }

    private BinanceExchange buildBinanceExchange() {
        return (BinanceExchange) ExchangeFactory.INSTANCE.createExchange(BinanceExchange.class.getName());
    }

}
