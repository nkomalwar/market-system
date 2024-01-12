package com.sentryc.marketsystem.repositories;

import com.sentryc.marketsystem.entities.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketPlaceRepository extends JpaRepository<Marketplace, String> {
    @Override
    Optional<Marketplace> findById(String id);
}
