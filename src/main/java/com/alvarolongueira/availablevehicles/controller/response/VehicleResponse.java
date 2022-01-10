package com.alvarolongueira.availablevehicles.controller.response;

import com.alvarolongueira.availablevehicles.domain.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse {

    private String uniqueId;

    private String name;

    private String licensePlate;

    private String type;

    private String companyId;

    public static VehicleResponse convert(Vehicle vehicle) {
        return new VehicleResponse(vehicle.getUniqueId(), vehicle.getName(), vehicle.getLicensePlate(), vehicle.getType(), vehicle.getCompanyId());
    }
}
