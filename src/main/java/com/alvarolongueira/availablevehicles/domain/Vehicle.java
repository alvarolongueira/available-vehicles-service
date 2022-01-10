package com.alvarolongueira.availablevehicles.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class Vehicle {

    private long id;

    private String name;

    private String licensePlate;

    private String type;

    private String companyId;

    private boolean available;

}
