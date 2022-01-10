package com.alvarolongueira.availablevehicles.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.alvarolongueira.availablevehicles.business.CalculateAvailableVehiclesService;

@Configuration
@EnableScheduling
public class ScheduleService {

    @Autowired
    private CalculateAvailableVehiclesService calculateAvailableVehiclesService;

    public ScheduleService() {

    }

    @Scheduled(fixedDelay = 60000)
    public void scheduleQueryAvailableVehicles() {
        this.calculateAvailableVehiclesService.checkAvailability();
    }
}
