package com.alvarolongueira.availablevehicles.repository.entity;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class VehicleEntity {

    @Id
    private String uniqueId;

    private String name;

    private String licensePlate;

    private String type;

    private String companyId;

    private boolean available;

}
