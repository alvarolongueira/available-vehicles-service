package com.alvarolongueira.availablevehicles.exception.error;

import com.alvarolongueira.availablevehicles.exception.AvailableVehiclesServiceException;

public class ProviderNotAvailableException extends AvailableVehiclesServiceException {

    private static final long serialVersionUID = -5121873973629201572L;

    public ProviderNotAvailableException(String message) {
        super(message);
    }


}
