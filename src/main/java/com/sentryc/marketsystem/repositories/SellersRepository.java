package com.sentryc.marketsystem.repositories;

import com.sentryc.marketsystem.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellersRepository extends JpaRepository<Seller, UUID> {

    List<Seller> findAllBySellerInfoId(UUID sellerInfoId);
    List<Seller> findAllBySellerInfoIdAndProducerIdIn(UUID sellerInfoId, List<UUID> producerIds);
}
