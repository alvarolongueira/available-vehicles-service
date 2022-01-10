package com.alvarolongueira.availablevehicles.mock;

import com.alvarolongueira.availablevehicles.domain.Vehicle;
import com.alvarolongueira.availablevehicles.repository.entity.VehicleEntity;

public class MockFactory {

    public static String UNIQUE_ID_A1 = "A1";
    public static String UNIQUE_ID_B2 = "B2";
    public static String UNIQUE_ID_C3 = "C3";

    public static Vehicle AVAILABLE_VEHICLE_A1 = build(UNIQUE_ID_A1, true);
    public static Vehicle NO_AVAILABLE_VEHICLE_A1 = build(UNIQUE_ID_A1, false);

    public static Vehicle AVAILABLE_VEHICLE_B2 = build(UNIQUE_ID_B2, true);
    public static Vehicle NO_AVAILABLE_VEHICLE_B2 = build(UNIQUE_ID_B2, false);

    public static VehicleEntity ENTITY_AVAILABLE_A1 = buildEntity(UNIQUE_ID_A1, true);
    public static VehicleEntity ENTITY_NO_AVAILABLE_B2 = buildEntity(UNIQUE_ID_B2, false);
    public static VehicleEntity ENTITY_AVAILABLE_C3 = buildEntity(UNIQUE_ID_C3, true);

    private static Vehicle build(String uniqueId, boolean available) {
        return new Vehicle(uniqueId, "nameA1", "licenseA1", "typeA1", "companyA1", available);
    }

    private static VehicleEntity buildEntity(String uniqueId, boolean available) {
        return new VehicleEntity(uniqueId, "nameA1", "licenseA1", "typeA1", "companyA1", available);
    }
}
