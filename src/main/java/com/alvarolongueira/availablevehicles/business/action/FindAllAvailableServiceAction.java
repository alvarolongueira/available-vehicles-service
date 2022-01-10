package com.alvarolongueira.availablevehicles.business.action;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvarolongueira.availablevehicles.business.FindAllAvailableService;
import com.alvarolongueira.availablevehicles.controller.response.FindAllAvailableResponse;
import com.alvarolongueira.availablevehicles.controller.response.VehicleResponse;
import com.alvarolongueira.availablevehicles.repository.VehicleEntityManager;

@Service
public class FindAllAvailableServiceAction implements FindAllAvailableService {

    @Autowired
    private VehicleEntityManager entityManager;

    public FindAllAvailableServiceAction() {

    }

    @Override
    public FindAllAvailableResponse find() {
        Set<VehicleResponse> list = this.entityManager.findAllAvailable().stream().map(VehicleResponse::convert).collect(Collectors.toSet());
        return new FindAllAvailableResponse(list);
    }
}
