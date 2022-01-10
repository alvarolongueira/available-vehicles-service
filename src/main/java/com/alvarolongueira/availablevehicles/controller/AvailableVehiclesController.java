package com.alvarolongueira.availablevehicles.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailableVehiclesController {

    @GetMapping(path = "/all")
    public ResponseEntity<String> findAll() {
        //TODO
        return null;
    }

}
