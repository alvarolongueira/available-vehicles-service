package com.alvarolongueira.availablevehicles.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvarolongueira.availablevehicles.controller.response.FindAllAvailableResponse;
import com.alvarolongueira.availablevehicles.controller.response.VehicleResponse;

@RestController
@RequestMapping("/available")
public class AvailableVehiclesController {

    @GetMapping
    public ResponseEntity<FindAllAvailableResponse> findAll() {
        //TODO
        return null;
    }

    @GetMapping(path = "/{vehicleId}")
    public ResponseEntity<VehicleResponse> findById(@PathVariable(name = "vehicleId") long vehicleId) {
        //TODO
        return null;
    }

}
