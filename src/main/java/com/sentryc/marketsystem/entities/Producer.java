package com.sentryc.marketsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "producers")
public class Producer {

    @Id
    private UUID id;
    private String name;
    private Date created_at;

}
