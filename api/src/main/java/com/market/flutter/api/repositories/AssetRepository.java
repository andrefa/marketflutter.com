package com.market.flutter.api.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.market.flutter.api.models.domain.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {


}
