package com.sentryc.marketsystem.repositories;

import com.sentryc.marketsystem.entities.Seller;
import com.sentryc.marketsystem.entities.SellerInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerRepository extends PagingAndSortingRepository<SellerInfo, UUID> {
    List<SellerInfo> findAllByName(String name, Pageable pageable);
    List<SellerInfo> findAllByMarketplaceIdIn(List<String> marketplaceId, Pageable pageable);
    List<SellerInfo> findAllByNameAndMarketplaceIdIn(String name, List<String> marketplaceId, Pageable pageable);

}
