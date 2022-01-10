package com.alvarolongueira.availablevehicles.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleResponse {

    private String uniqueId;

    private String name;

    private String licensePlate;

    private String type;

    private String companyId;

}
