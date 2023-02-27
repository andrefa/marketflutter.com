package com.market.flutter.api.models.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record UserAssetConfig(
        String exchangeCoinSymbol,
        BigDecimal basePrice,
        BigDecimal lastSeenPrice,
        BigDecimal thresholdForBuying,
        BigDecimal buyingAmount,
        BigDecimal thresholdForSelling,
        boolean buyEnabled,
        boolean sellEnabled,
        Integer maxHoldingTransactions) {
}
