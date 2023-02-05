package com.market.flutter.api.models.api.binance;

import java.util.List;

public record ExchangeInfo(
        String timezone,
        Long serverTime,
        List<RateLimit> rateLimits,
        List<Symbol> symbols) {
}