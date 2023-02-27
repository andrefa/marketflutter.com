package com.market.flutter.api.models.dto;


import java.math.BigDecimal;

import com.market.flutter.api.models.domain.TradeStrategy;

import lombok.Builder;

@Builder
public record UserAsset(
        String coinSymbol,
        BigDecimal balance,
        TradeStrategy tradeStrategy,
        UserAssetConfig userAssetConfig) {
}
