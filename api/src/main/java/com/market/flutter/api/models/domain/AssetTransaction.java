package com.market.flutter.api.models.domain;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asset_transactions")
public class AssetTransaction extends BaseEntity {

    @ManyToOne(optional = false,
            targetEntity = Asset.class,
            cascade = {CascadeType.DETACH})
    private Asset asset;

    @OneToOne(optional = false)
    private Coin coinIn;

    @OneToOne(optional = false)
    private Coin coinOut;

    @OneToOne(optional = true)
    private AssetConfig assetConfig;

    @Column(name = "transaction_type",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "purchase_price",
            nullable = false)
    private BigDecimal purchasePrice;

    @Column(name = "amount",
            nullable = false)
    private BigDecimal amount;



}
