package com.alvarolongueira.availablevehicles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvarolongueira.availablevehicles.business.FindAllAvailableService;
import com.alvarolongueira.availablevehicles.business.action.FindVehicleServiceAction;
import com.alvarolongueira.availablevehicles.controller.response.FindAllAvailableResponse;
import com.alvarolongueira.availablevehicles.controller.response.ResponseBody;
import com.alvarolongueira.availablevehicles.controller.response.VehicleResponse;

@RestController
@RequestMapping("/available")
public class AvailableVehiclesController {

    @Autowired
    private FindAllAvailableService findAllAvailableService;

    @Autowired
    private FindVehicleServiceAction findVehicleServiceAction;

    public AvailableVehiclesController() {

    }

    @GetMapping
    public ResponseEntity<FindAllAvailableResponse> findAllAvailable() {
        return ResponseBody.of(this.findAllAvailableService.find());
    }

    @GetMapping(path = "/{vehicleId}")
    public ResponseEntity<VehicleResponse> findById(@PathVariable(name = "vehicleId") String vehicleId) {
        return ResponseBody.of(this.findVehicleServiceAction.findById(vehicleId));
    }

}
