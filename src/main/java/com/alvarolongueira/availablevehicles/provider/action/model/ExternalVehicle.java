package com.alvarolongueira.availablevehicles.provider.action.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalVehicle {

    private String id;
    private String name;
    private String x;
    private String y;
    private String licencePlate;
    private String range;
    private String batteryLevel;
    private String helmets;
    private String model;
    private String resourceImageId;
    private List<String> resourcesImagesUrls;
    private String realTimeData;
    private String resourceType;
    private String companyZoneId;

}
