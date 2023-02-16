package com.market.flutter.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.flutter.api.models.domain.Coin;


public interface CoinRepository extends JpaRepository<Coin, Long> {

}
