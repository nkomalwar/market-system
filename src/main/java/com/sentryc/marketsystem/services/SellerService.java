package com.sentryc.marketsystem.services;

import com.sentryc.marketsystem.SellerFilter;
import com.sentryc.marketsystem.SellerSortByEnum;
import com.sentryc.marketsystem.entities.SellerInfo;
import com.sentryc.marketsystem.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public List<SellerInfo> getSellers(SellerFilter filter, Pageable pageRequest) {
        List<SellerInfo> sellers;
        if (StringUtils.hasLength(filter.getSearchByName())
                && !CollectionUtils.isEmpty(filter.getMarketplaceIds())) {
            sellers = sellerRepository.findAllByNameAndMarketplaceIdIn(
                    filter.getSearchByName(),
                    filter.getMarketplaceIds(), pageRequest
            );
        } else if (StringUtils.hasLength(filter.getSearchByName())){
            sellers = sellerRepository.findAllByName(filter.getSearchByName(), pageRequest);
        } else {
            sellers = sellerRepository.findAllByMarketplaceIdIn(filter.getMarketplaceIds(), pageRequest);
        }
        return sellers;
    }
}
