package com.market.flutter.api.models.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @OneToOne(optional = false)
    private AssetConfig assetConfig;
    
    @ManyToOne(optional = false)
    private User user;
    
    @Column(name = "crypto_name", nullable = false)
    private String cryptoName;

    @OneToMany
    private List<AssetTransaction> assetTransactions;
    
}