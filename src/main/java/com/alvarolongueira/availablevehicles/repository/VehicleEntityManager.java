package com.alvarolongueira.availablevehicles.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.alvarolongueira.availablevehicles.domain.Vehicle;

public interface VehicleEntityManager {

    Optional<Vehicle> find(String uniqueId);

    Set<Vehicle> findAllAvailables();

    @Transactional
    void save(Vehicle vehicle);

    @Transactional
    void updateAvailability(String uniqueId, boolean available);

}
