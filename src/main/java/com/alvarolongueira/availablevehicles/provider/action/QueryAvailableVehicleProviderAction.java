package com.alvarolongueira.availablevehicles.provider.action;

import java.net.URI;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alvarolongueira.availablevehicles.domain.Vehicle;
import com.alvarolongueira.availablevehicles.exception.error.ProviderNotAvailableException;
import com.alvarolongueira.availablevehicles.provider.QueryAvailableVehicleProvider;
import com.alvarolongueira.availablevehicles.provider.action.model.ExternalVehicle;
import lombok.NonNull;

@Service
public class QueryAvailableVehicleProviderAction implements QueryAvailableVehicleProvider {

    private URI queryUri;

    @Autowired
    private RestTemplateBuilder restTemplate;

    public QueryAvailableVehicleProviderAction(@Value("${external.system.vehicles.uri}") @NonNull URI queryUri) {
        this.queryUri = queryUri;
    }

    @Override
    public Set<Vehicle> getVehicles() {
        try {

            ResponseEntity<ExternalVehicle[]> responseEntity = this.restTemplate.build().getForEntity(this.queryUri, ExternalVehicle[].class);
            if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                throw new ProviderNotAvailableException("Error remote service " + responseEntity.getStatusCode());
            }

            return Arrays.stream(responseEntity.getBody()).map(this::convert).collect(Collectors.toSet());

        } catch (Exception e) {
            throw new ProviderNotAvailableException(e.getMessage());
        }
    }

    private Vehicle convert(ExternalVehicle current) {
        return new Vehicle(current.getId(), current.getName(), current.getLicencePlate(), current.getResourceType(), current.getCompanyZoneId(), true);
    }
}
