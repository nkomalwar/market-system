package com.sentryc.marketsystem.controller;

import com.sentryc.marketsystem.PageInput;
import com.sentryc.marketsystem.ProducerSellerState;
import com.sentryc.marketsystem.SellerFilter;
import com.sentryc.marketsystem.SellerPageableResponse;
import com.sentryc.marketsystem.SellerSortByEnum;
import com.sentryc.marketsystem.entities.SellerInfo;
import com.sentryc.marketsystem.services.ProducerSellerService;
import com.sentryc.marketsystem.services.SellerService;
import graphql.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class DataController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ProducerSellerService producerSellerService;

    @QueryMapping
    public SellerPageableResponse sellers(@Argument SellerFilter filter,@Argument PageInput page,
                                          @Argument SellerSortByEnum sortBy, DataFetchingEnvironment environment) {
        log.info("Retrieving all data with filter" + filter);
        GraphQLContext context = environment.getContext();
        context.put("sellerFilter", filter);
        List<SellerInfo> sellers = sellerService.getSellers(filter, getPageableFrom(page, sortBy));

        return SellerPageableResponse.builder()
                .data(sellers)
                .build();
    }

    @SchemaMapping(typeName="Seller", field = "producerSellerStates")
    public List<ProducerSellerState> producerSellerState(SellerInfo sellerInfo, DataFetchingEnvironment environment) {
        // get filters
        GraphQLContext context = environment.getContext();
        SellerFilter sellerFilter = context.get("sellerFilter");
        List<SelectedField> selectedFields = environment.getSelectionSet().getFields();
        List<ProducerSellerState> producerSellerStateList = producerSellerService.getProducerSellerStates(sellerInfo, sellerFilter, selectedFields);

        return producerSellerStateList;
    }

    private Pageable getPageableFrom(PageInput pageInput, SellerSortByEnum sortByEnum) {
        Sort sort = null;
        switch (sortByEnum) {
            case NAME_ASC -> sort = Sort.by("name").ascending();
            case NAME_DESC -> sort = Sort.by("name").descending();
            case MARKETPLACE_ID_ASC -> sort = Sort.by("marketplaceId").ascending();
            case MARKETPLACE_ID_DESC -> sort = Sort.by("marketplaceId").descending();
            case SELLER_INFO_EXTERNAL_ID_ASC -> sort = Sort.by("id").ascending();
            case SELLER_INFO_EXTERNAL_ID_DESC -> sort = Sort.by("id").descending();
        }
        if (Objects.isNull(sort)) {
            return PageRequest.of(
                    pageInput.getPage() == null ? 0 : pageInput.getPage(),
                    pageInput.getSize() == null ? 5 : pageInput.getSize());
        } else {
            return PageRequest.of(
                    pageInput.getPage() == null ? 0 : pageInput.getPage(),
                    pageInput.getSize() == null ? 5 : pageInput.getSize(),
                    sort);
        }
    }
}
