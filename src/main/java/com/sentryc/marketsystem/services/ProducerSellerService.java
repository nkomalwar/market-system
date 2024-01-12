package com.sentryc.marketsystem.services;

import com.sentryc.marketsystem.ProducerSellerState;
import com.sentryc.marketsystem.SellerFilter;
import com.sentryc.marketsystem.entities.Producer;
import com.sentryc.marketsystem.entities.Seller;
import com.sentryc.marketsystem.entities.SellerInfo;
import com.sentryc.marketsystem.repositories.ProducerRepository;
import com.sentryc.marketsystem.repositories.SellersRepository;
import graphql.schema.SelectedField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProducerSellerService {

    @Autowired
    private SellersRepository sellersRepository;
    @Autowired
    private ProducerRepository producerRepository;

    public List<ProducerSellerState> getProducerSellerStates(SellerInfo sellerInfo, SellerFilter sellerFilter, List<SelectedField> selectedFields) {
        boolean fetchProducerData = selectedFields.stream()
                .anyMatch(x -> x.getQualifiedName().equals("producerId") || x.getQualifiedName().equals("producerName"));

        List<Seller> sellers;
        if (CollectionUtils.isEmpty(sellerFilter.getProducerIds())) {
            sellers = sellersRepository.findAllBySellerInfoId(sellerInfo.getId());
        } else {
            sellers = sellersRepository.findAllBySellerInfoIdAndProducerIdIn(sellerInfo.getId(), sellerFilter.getProducerIds());
        }

        List<ProducerSellerState> producerSellerStateList = new ArrayList<>();
        for (Seller seller: sellers) {
            Optional<Producer> optional = fetchProducerData
                    ? producerRepository.findById(seller.getProducerId())
                    : Optional.empty();
            producerSellerStateList.add(getProducerSellerState(seller, optional));
        }
        return producerSellerStateList;
    }

    private ProducerSellerState getProducerSellerState(Seller seller, Optional<Producer> producer) {
        ProducerSellerState.ProducerSellerStateBuilder builder = ProducerSellerState.builder()
                .sellerState(seller.getState())
                .sellerId(seller.getId());
        if (producer.isPresent()) {
            builder.producerId(producer.get().getId())
                    .producerName(producer.get().getName());
        }
        return builder.build();
    }
}
