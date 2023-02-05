package com.market.flutter.api.models.api.binance;

public record RateLimit(
        String rateLimitType,
        String interval,
        Integer intervalNum,
        Integer limit) {
}
