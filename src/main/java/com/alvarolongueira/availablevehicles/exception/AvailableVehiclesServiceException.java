package com.alvarolongueira.availablevehicles.exception;

public abstract class AvailableVehiclesServiceException extends RuntimeException {

    private static final long serialVersionUID = -5733831896795514291L;

    public AvailableVehiclesServiceException(String message) {
        super(message);
    }


}
