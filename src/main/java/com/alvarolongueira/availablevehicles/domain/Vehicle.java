package com.alvarolongueira.availablevehicles.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class Vehicle {

    private String uniqueId;

    private String name;

    private String licensePlate;

    private String type;

    private String companyId;

    @Builder.Default
    private boolean available = true;

}
