package com.market.flutter.api.models.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "coins")
@ToString
public class Coin extends BaseEntity {

    @Column(name = "crypto_name")
    private String cryptoName;

    @Column(name = "crypto_code")
    private String cryptoCode;

    @Column(name = "interest_level",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private InterestLevel interestLevel;

}
