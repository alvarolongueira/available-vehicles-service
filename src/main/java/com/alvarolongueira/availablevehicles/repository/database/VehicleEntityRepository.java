package com.alvarolongueira.availablevehicles.repository.database;

import org.springframework.data.repository.CrudRepository;

import com.alvarolongueira.availablevehicles.repository.entity.VehicleEntity;

public interface VehicleEntityRepository extends CrudRepository<VehicleEntity, String> {

}
