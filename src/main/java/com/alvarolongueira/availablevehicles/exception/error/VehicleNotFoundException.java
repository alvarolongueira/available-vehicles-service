package com.alvarolongueira.availablevehicles.exception.error;

import com.alvarolongueira.availablevehicles.exception.AvailableVehiclesServiceException;

public class VehicleNotFoundException extends AvailableVehiclesServiceException {

    private static final long serialVersionUID = 8339279866894852611L;

    public VehicleNotFoundException(String message) {
        super(message);
    }
}
