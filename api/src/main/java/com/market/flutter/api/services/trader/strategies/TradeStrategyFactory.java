package com.market.flutter.api.services.trader.strategies;

import org.springframework.stereotype.Component;

import com.market.flutter.api.models.domain.Asset;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TradeStrategyFactory {

    private final DefaultTradeStrategy defaultTradeStrategy;

    public TradeStrategyExecutor getStrategy(Asset asset) {
        log.debug("TradeStrategyFactory.getStrategy() called with asset {}", asset);
        return defaultTradeStrategy;
    }

}
