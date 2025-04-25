package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Location() {}

}
