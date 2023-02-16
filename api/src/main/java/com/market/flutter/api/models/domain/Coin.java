package com.market.flutter.api.models.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coins")
public class Coin {

    @Column(name = "crypto_name")
    private String cryptoName;

    @Column(name = "crypto_code")
    private String cryptoCode;

}
