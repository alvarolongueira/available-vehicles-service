package com.alvarolongueira.availablevehicles.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VEHICLE")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {

    @Id
    private String uniqueId;

    private String name;

    private String licensePlate;

    private String type;

    private String companyId;

    private boolean available;

}
