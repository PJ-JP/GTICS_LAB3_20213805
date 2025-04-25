package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(name = "country_id")
    private String id;

    private String countryName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    public Country() {}
}

