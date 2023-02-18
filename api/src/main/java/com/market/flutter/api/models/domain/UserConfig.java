package com.market.flutter.api.models.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_configs")
public class UserConfig extends BaseEntity {

    @OneToOne
    private User user;

    @Column(name = "binance_api_key")
    private String binanceApiKey;

    @Column(name = "binance_secret_key")
    private String binanceSecretKey;

    @Column(name = "ntfy_topic_name")
    private String ntfyTopicName;

    @Column(name = "enable_execute_transactions")
    private boolean executeTransactionsEnabled;

}
