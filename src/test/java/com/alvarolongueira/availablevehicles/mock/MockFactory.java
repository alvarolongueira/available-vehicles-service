package com.alvarolongueira.availablevehicles.mock;

import com.alvarolongueira.availablevehicles.domain.Vehicle;

public class MockFactory {

    public static String UNIQUE_ID_A1 = "A1";
    private static String UNIQUE_ID_B2 = "B2";

    public static Vehicle AVAILABLE_VEHICLE_A1 = build(UNIQUE_ID_A1, true);
    public static Vehicle NO_AVAILABLE_VEHICLE_A1 = build(UNIQUE_ID_A1, false);

    public static Vehicle AVAILABLE_VEHICLE_B2 = build(UNIQUE_ID_B2, true);
    public static Vehicle NO_AVAILABLE_VEHICLE_B2 = build(UNIQUE_ID_B2, false);

    private static Vehicle build(String uniqueId, boolean available) {
        return new Vehicle(uniqueId, "nameA1", "licenseA1", "typeA1", "companyA1", available);
    }

}
