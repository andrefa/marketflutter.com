package com.market.flutter.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.flutter.api.models.domain.AssetTransaction;

@Repository
public interface AssetTransactionRepository extends JpaRepository<AssetTransaction, Long> {
}
