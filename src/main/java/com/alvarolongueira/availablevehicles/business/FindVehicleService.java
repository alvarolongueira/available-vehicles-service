package com.alvarolongueira.availablevehicles.business;

import com.alvarolongueira.availablevehicles.controller.response.VehicleResponse;

public interface FindVehicleService {

    VehicleResponse findById(String uniqueId);

}
