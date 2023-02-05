package com.market.flutter.api.models.api.binance;

import java.util.List;

public record Symbol(
        String symbol,
        String status,
        String baseAsset,
        Integer baseAssetPrecision,
        String quoteAsset,
        Integer quotePrecision,
        Integer quoteAssetPrecision,
        Integer baseCommissionPrecision,
        Integer quoteCommissionPrecision,
        List<String> orderTypes,
        Boolean icebergAllowed,
        Boolean ocoAllowed,
        Boolean quoteOrderQtyMarketAllowed,
        Boolean allowTrailingStop,
        Boolean cancelReplaceAllowed,
        Boolean isSpotTradingAllowed,
        Boolean isMarginTradingAllowed,
        List<Filter> filters,
        List<String> permissions,
        String defaultSelfTradePreventionMode,
        List<String> allowedSelfTradePreventionModes) {
}
