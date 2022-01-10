package com.alvarolongueira.availablevehicles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AvailableVehiclesServiceExceptionHandler {

    @ExceptionHandler(AvailableVehiclesServiceException.class)
    public ResponseEntity<String> handleClientException(AvailableVehiclesServiceException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

}
