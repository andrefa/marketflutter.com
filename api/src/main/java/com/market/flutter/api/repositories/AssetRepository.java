package com.market.flutter.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.market.flutter.api.models.domain.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long>{
    
    @Query(" SELECT distinct ass.cryptoName FROM Asset ass ")
    List<String> tradedAssets();

}
