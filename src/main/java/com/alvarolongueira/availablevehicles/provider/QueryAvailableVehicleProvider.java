package com.alvarolongueira.availablevehicles.provider;

import java.util.Set;

import com.alvarolongueira.availablevehicles.domain.Vehicle;

public interface QueryAvailableVehicleProvider {

    Set<Vehicle> getVehicles();

}
