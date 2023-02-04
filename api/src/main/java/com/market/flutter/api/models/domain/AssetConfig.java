package com.market.flutter.api.models.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asset_configs")
public class AssetConfig extends BaseEntity {

    @Column(name = "base_price",
            nullable = false)
    private BigDecimal basePrice;

    @Column(name = "dips_before_buying",
            nullable = false)
    private Integer priceDipsBeforeBuying;

    @Column(name = "buying_threshold",
            nullable = false)
    private BigDecimal thresholdForBuying;

    @Column(name = "selling_threshold",
            nullable = false)
    private BigDecimal thresholdForSelling;

    @Column(name = "enable_buy",
            nullable = false)
    private Boolean enableBuy;

    @Column(name = "enable_sell",
            nullable = false)
    private Boolean enableSell;

}
