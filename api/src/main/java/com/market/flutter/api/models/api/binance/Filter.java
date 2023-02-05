package com.market.flutter.api.models.api.binance;

public record Filter(String filterType,
        String minPrice,
        String maxPrice,
        String tickSize,
        String minQty,
        String maxQty,
        String stepSize,
        String minNotional,
        Boolean applyToMarket,
        Integer avgPriceMins,
        Integer limit,
        Integer minTrailingAboveDelta,
        Integer maxTrailingAboveDelta,
        Integer minTrailingBelowDelta,
        Integer maxTrailingBelowDelta,
        String bidMultiplierUp,
        String bidMultiplierDown,
        String askMultiplierUp,
        String askMultiplierDown,
        Integer maxNumOrders,
        Integer maxNumAlgoOrders) {
}
