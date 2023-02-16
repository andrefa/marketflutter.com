package com.market.flutter.api.models.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assets")
public class Asset extends BaseEntity {



    @ManyToOne(optional = false)
    private User user;

    @OneToOne
    private Coin coin;

    @OneToOne(optional = false)
    private AssetConfig assetConfig;

    @Column(name = "trade_strategy",
            nullable = false)
            @Enumerated(EnumType.STRING)
    private TradeStrategy tradeStrategy;

    @OneToMany
    private List<AssetTransaction> assetTransactions;

}
