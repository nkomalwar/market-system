package com.sentryc.marketsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "marketplaces")
public class Marketplace {
    @Id
    private String id;
    private String description;
}
