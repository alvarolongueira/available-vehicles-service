package com.alvarolongueira.availablevehicles.provider.action;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.alvarolongueira.availablevehicles.domain.Vehicle;
import com.alvarolongueira.availablevehicles.provider.QueryAvailableVehicleProvider;

@Service
public class QueryAvailableVehicleProviderAction implements QueryAvailableVehicleProvider {

    public QueryAvailableVehicleProviderAction() {
        
    }

    @Override
    public Set<Vehicle> getVehicles() {
        return null;
    }
}
