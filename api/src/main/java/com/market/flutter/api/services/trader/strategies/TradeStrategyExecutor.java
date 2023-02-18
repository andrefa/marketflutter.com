package com.market.flutter.api.services.trader.strategies;

import java.math.BigDecimal;

import com.market.flutter.api.models.domain.Asset;

public interface TradeStrategyExecutor {

    void execute(Asset asset, BigDecimal lastPrice);

}
