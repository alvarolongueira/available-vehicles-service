package com.alvarolongueira.availablevehicles.repository;

import java.util.Optional;
import java.util.Set;

import com.alvarolongueira.availablevehicles.domain.Vehicle;

public interface VehicleEntityManager {

    Optional<Vehicle> find(String uniqueId);

    Set<Vehicle> findAll();

    Set<Vehicle> findAllAvailable();

    void insert(Vehicle vehicle);

    void updateAvailability(String uniqueId, boolean available);

}
