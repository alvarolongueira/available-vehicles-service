package com.alvarolongueira.availablevehicles.exception.error;

import com.alvarolongueira.availablevehicles.exception.AvailableVehiclesServiceException;

public class DatabaseServiceException extends AvailableVehiclesServiceException {

    private static final long serialVersionUID = -1570703397078944200L;

    public DatabaseServiceException(String message) {
        super(message);
    }


}
