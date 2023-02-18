package com.market.flutter.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.flutter.api.models.domain.Coin;
import com.market.flutter.api.models.domain.InterestLevel;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

    List<Coin> findByInterestLevel(InterestLevel interestLevel);

}
