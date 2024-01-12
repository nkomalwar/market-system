package com.sentryc.marketsystem;

import com.sentryc.marketsystem.entities.SellerInfo;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;


@SpringBootTest
@AutoConfigureHttpGraphQlTester
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MarketSystemApplicationTests {

    @Autowired
    HttpGraphQlTester httpGraphQlTester;
    @Test
    @Order(1)
    void getSeller(){
        String query = String.format("""
                query MyQuery {
                   sellers(
                     filter: {searchByName: "amazon us", marketplaceIds: "amazon.ae"}
                     sortBy: NAME_ASC
                     page: {page: 0, size: 2}
                   ) {
                     data {
                       externalId
                       marketplaceId
                       name
                       producerSellerStates {
                         producerId
                         producerName
                         sellerId
                         sellerState
                       }
                     }
                   }
                 }
                """);
        SellerPageableResponse sellerPageableResponse = this.httpGraphQlTester
                .document(query)
                .execute()
                .errors()
                .verify()
                .path("sellers")
                .entity(SellerPageableResponse.class)
                .get();
        Assert.assertNotNull(sellerPageableResponse);
        Assert.assertEquals(sellerPageableResponse.getData().size(), 1);

        SellerInfo sellerInfo = sellerPageableResponse.getData().get(0);
        Assert.assertEquals(sellerInfo.getName(), "amazon us");
        Assert.assertEquals(sellerInfo.getExternalId(), "A2QUTRSO1ZHRN9");
        Assert.assertEquals(sellerInfo.getMarketplaceId(), "amazon.ae");
    }
}
