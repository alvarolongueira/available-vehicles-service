package com.alvarolongueira.availablevehicles.repository.database;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvarolongueira.availablevehicles.domain.Vehicle;
import com.alvarolongueira.availablevehicles.exception.error.DatabaseServiceException;
import com.alvarolongueira.availablevehicles.repository.VehicleEntityManager;
import com.alvarolongueira.availablevehicles.repository.entity.VehicleEntity;

@Service
public class VehicleEntityManagerImpl implements VehicleEntityManager {

    @Autowired
    private VehicleEntityRepository repository;

    public VehicleEntityManagerImpl() {

    }

    @Override
    public Optional<Vehicle> find(String uniqueId) {
        return this.repository.findById(uniqueId).map(this::convert);
    }

    @Override
    public Set<Vehicle> findAll() {
        Set<Vehicle> list = new HashSet<>();
        this.repository.findAll().forEach(current -> list.add(this.convert(current)));
        return list;
    }

    @Override
    public Set<Vehicle> findAllAvailable() {
        return this.findAll().stream().filter(current -> current.isAvailable()).collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public void insert(Vehicle vehicle) {
        Optional<VehicleEntity> entity = this.repository.findById(vehicle.getUniqueId());
        if (entity.isPresent()) {
            throw new DatabaseServiceException("No exists vehicle with id:" + vehicle.getUniqueId());
        }

        VehicleEntity newEntity = this.convert(vehicle);
        this.repository.save(newEntity);
    }

    @Transactional
    @Override
    public void updateAvailability(String uniqueId, boolean available) {
        VehicleEntity entity = this.repository.findById(uniqueId).orElseThrow(() -> new DatabaseServiceException("No exists vehicle with id:" + uniqueId));
        entity.setAvailable(available);
        this.repository.save(entity);
    }

    private Vehicle convert(VehicleEntity entity) {
        return new Vehicle(entity.getUniqueId(), entity.getName(), entity.getLicensePlate(), entity.getType(), entity.getCompanyId(), entity.isAvailable());
    }

    private VehicleEntity convert(Vehicle vehicle) {
        return new VehicleEntity(vehicle.getUniqueId(), vehicle.getName(), vehicle.getLicensePlate(), vehicle.getType(), vehicle.getCompanyId(), vehicle.isAvailable());
    }
}
