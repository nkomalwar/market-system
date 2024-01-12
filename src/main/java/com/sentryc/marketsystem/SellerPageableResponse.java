package com.sentryc.marketsystem;

import com.sentryc.marketsystem.entities.SellerInfo;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class SellerPageableResponse {
    String meta;
    List<SellerInfo> data;
}
