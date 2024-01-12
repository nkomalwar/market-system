package com.sentryc.marketsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "seller_infos")
public class SellerInfo {

    @Id
    @Column(name = "id")
    private UUID id;
    private String marketplaceId;
    private String name;
    private String url;
    private String country;
    private String externalId;

}
