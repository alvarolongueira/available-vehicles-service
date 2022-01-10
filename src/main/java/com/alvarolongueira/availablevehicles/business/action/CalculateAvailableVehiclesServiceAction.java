package com.alvarolongueira.availablevehicles.business.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvarolongueira.availablevehicles.business.CalculateAvailableVehiclesService;
import com.alvarolongueira.availablevehicles.provider.QueryAvailableVehicleProvider;
import com.alvarolongueira.availablevehicles.repository.VehicleEntityManager;

@Service
public class CalculateAvailableVehiclesServiceAction implements CalculateAvailableVehiclesService {

    @Autowired
    private VehicleEntityManager entityManager;

    @Autowired
    private QueryAvailableVehicleProvider provider;

    public CalculateAvailableVehiclesServiceAction() {

    }

    @Override
    public void checkAvailability() {

    }

}
