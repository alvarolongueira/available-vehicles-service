package com.alvarolongueira.availablevehicles.business.action;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvarolongueira.availablevehicles.business.CalculateAvailableVehiclesService;
import com.alvarolongueira.availablevehicles.domain.Vehicle;
import com.alvarolongueira.availablevehicles.provider.QueryAvailableVehicleProvider;
import com.alvarolongueira.availablevehicles.repository.VehicleEntityManager;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CalculateAvailableVehiclesServiceAction implements CalculateAvailableVehiclesService {

    @Autowired
    private VehicleEntityManager entityManager;

    @Autowired
    private QueryAvailableVehicleProvider provider;

    public CalculateAvailableVehiclesServiceAction() {

    }

    @Override
    public void checkAvailability() {
        Set<Vehicle> rawList = this.provider.getVehicles();
        this.updateNoAvailable(rawList);
        this.insertOrUpdate(rawList);
    }

    private void insertOrUpdate(Set<Vehicle> list) {
        for (Vehicle current : list) {

            String uniqueId = current.getUniqueId();

            try {
                Optional<Vehicle> vehicle = this.entityManager.find(uniqueId);

                if (!vehicle.isPresent()) {
                    this.entityManager.insert(current);

                } else if (!vehicle.get().isAvailable()) {
                    this.entityManager.updateAvailability(uniqueId, true);
                }

            } catch (Exception e) {
                log.error("Error updating vehicle with id: " + uniqueId);
            }
        }
    }

    private void updateNoAvailable(Set<Vehicle> list) {
        Set<String> previousAvailableList = this.entityManager.findAllAvailable().stream().map(Vehicle::getUniqueId).collect(Collectors.toSet());
        Set<String> uniqueIdNewList = list.stream().map(Vehicle::getUniqueId).collect(Collectors.toSet());
        Set<String> noAvailableList = previousAvailableList.stream().filter(current -> !uniqueIdNewList.contains(current)).collect(Collectors.toSet());

        for (String uniqueId : noAvailableList) {
            try {
                this.entityManager.updateAvailability(uniqueId, false);
            } catch (Exception e) {
                log.error("Error updating vehicle with id: " + uniqueId);
            }
        }
    }

}
