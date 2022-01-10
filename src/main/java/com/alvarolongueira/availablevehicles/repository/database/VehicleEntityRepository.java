package com.alvarolongueira.availablevehicles.repository.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alvarolongueira.availablevehicles.repository.entity.VehicleEntity;

@Repository
public interface VehicleEntityRepository extends CrudRepository<VehicleEntity, String> {

}
