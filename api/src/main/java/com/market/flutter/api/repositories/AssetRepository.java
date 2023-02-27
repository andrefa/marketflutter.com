package com.market.flutter.api.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.flutter.api.models.domain.Asset;
import com.market.flutter.api.models.domain.Coin;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    List<Asset> findByCoin(Coin coin);

    @Query("SELECT a FROM Asset a WHERE a.user.email = :email")
    List<Asset> listByUserUserEmail(String email);

}
