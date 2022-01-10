package com.alvarolongueira.availablevehicles.business.action;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvarolongueira.availablevehicles.business.FindVehicleService;
import com.alvarolongueira.availablevehicles.controller.response.VehicleResponse;
import com.alvarolongueira.availablevehicles.domain.Vehicle;
import com.alvarolongueira.availablevehicles.exception.error.VehicleNotFoundException;
import com.alvarolongueira.availablevehicles.repository.VehicleEntityManager;

@Service
public class FindVehicleServiceAction implements FindVehicleService {

    @Autowired
    private VehicleEntityManager entityManager;

    public FindVehicleServiceAction() {

    }

    @Override
    public VehicleResponse findById(String uniqueId) {
        Optional<Vehicle> vehicle = this.entityManager.find(uniqueId);
        if (!vehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle not found  with id: " + uniqueId);
        }

        return VehicleResponse.convert(vehicle.get());
    }
}
