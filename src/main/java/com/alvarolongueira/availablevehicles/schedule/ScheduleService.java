package com.alvarolongueira.availablevehicles.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.alvarolongueira.availablevehicles.business.QueryAvailableVehiclesService;

@Configuration
@EnableScheduling
public class ScheduleService {

    @Autowired
    private QueryAvailableVehiclesService queryAvailableVehiclesService;

    public ScheduleService() {

    }

    @Scheduled(fixedDelay = 1000)
    public void scheduleQueryAvailableVehicles() {
        this.queryAvailableVehiclesService.checkAvailability();
    }
}
