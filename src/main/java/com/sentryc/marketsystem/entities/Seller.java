package com.sentryc.marketsystem.entities;

import com.sentryc.marketsystem.SellerState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "sellers")
public class Seller {

    @Id
    private UUID id;
    private UUID producerId;
    private UUID sellerInfoId;
    @Enumerated(EnumType.STRING)
    private SellerState state;

}
