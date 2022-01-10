package com.alvarolongueira.availablevehicles.business.action;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.alvarolongueira.availablevehicles.repository.VehicleEntityManager;

public class QueryAvailableVehiclesServiceActionTest {

    @Mock
    private VehicleEntityManager entityManager;

    @Before
    public void setUp() {

    }

    @Test
    public void no_exists_then_create_as_available() {

    }

    @Test
    public void no_available_to_available() {

    }

    @Test
    public void available_to_no_available() {

    }

    @Test
    public void error_database_only_rollback_that() {

    }

    @Test
    public void error_query_external_service() {

    }

}
