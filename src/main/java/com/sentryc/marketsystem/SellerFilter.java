package com.sentryc.marketsystem;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@ToString
public class SellerFilter {
    String searchByName;
    List<UUID> producerIds;
    List<String> marketplaceIds;

}
