package com.market.flutter.api.models.dto;


import lombok.Builder;

@Builder
public record AvailableAsset(
        String symbol,
        String status,
        String baseAsset,
        String quoteAsset) {
}
