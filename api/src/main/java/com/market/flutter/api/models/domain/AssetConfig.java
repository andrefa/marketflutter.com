package com.market.flutter.api.models.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asset_configs")
public class AssetConfig extends BaseEntity {

        @OneToOne
        private Coin coin;

    @Column(name = "base_price",
            nullable = false)
    private BigDecimal basePrice;

    @Column(name = "buying_percentage_threshold",
            nullable = false)
    private BigDecimal thresholdForBuying;

    @Column(name = "buying_amount",
    nullable = false)
private BigDecimal buyingAmount;

    @Column(name = "selling_percentage_threshold",
            nullable = false)
    private BigDecimal thresholdForSelling;

    @Column(name = "enable_buy",
            nullable = false)
    private Boolean enableBuy;

    @Column(name = "enable_sell",
            nullable = false)
    private Boolean enableSell;

        @Column(name = "max_holding_transactions")
    private Integer maxHoldingTransactions;

}
