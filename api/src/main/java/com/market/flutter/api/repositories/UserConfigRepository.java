package com.market.flutter.api.repositories;

import com.market.flutter.api.models.domain.UserConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConfigRepository extends JpaRepository<UserConfig, Long> {
}
