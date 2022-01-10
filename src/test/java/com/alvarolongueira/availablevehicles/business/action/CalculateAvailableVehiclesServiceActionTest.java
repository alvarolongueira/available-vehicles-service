package com.alvarolongueira.availablevehicles.business.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.alvarolongueira.availablevehicles.business.CalculateAvailableVehiclesService;
import com.alvarolongueira.availablevehicles.domain.Vehicle;
import com.alvarolongueira.availablevehicles.mock.MockFactory;
import com.alvarolongueira.availablevehicles.provider.QueryAvailableVehicleProvider;
import com.alvarolongueira.availablevehicles.repository.VehicleEntityManager;

public class CalculateAvailableVehiclesServiceActionTest {

    @Mock
    private VehicleEntityManager entityManager;

    @Mock
    private QueryAvailableVehicleProvider provider;

    @InjectMocks
    private final CalculateAvailableVehiclesService service = new CalculateAvailableVehiclesServiceAction();

    @Before
    public void setUp() {

    }

    @Test
    public void no_exists_then_create_as_available() {
        Mockito
                .when(this.entityManager.find(MockFactory.UNIQUE_ID_A1))
                .thenReturn(Optional.empty());
        Mockito
                .when(this.provider.getVehicles())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_A1));

        this.service.checkAvailability();

        Mockito.verify(this.entityManager, Mockito.times(1)).find(MockFactory.UNIQUE_ID_A1);
        Mockito.verify(this.entityManager, Mockito.times(1)).save(MockFactory.AVAILABLE_VEHICLE_A1);
    }

    @Test
    public void no_available_to_available() {
        Mockito
                .when(this.entityManager.find(MockFactory.UNIQUE_ID_A1))
                .thenReturn(Optional.empty());
        Mockito
                .when(this.provider.getVehicles())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_A1));

        this.service.checkAvailability();

        Mockito.verify(this.entityManager, Mockito.times(1)).find(MockFactory.UNIQUE_ID_A1);
        Mockito.verify(this.entityManager, Mockito.times(1)).updateAvailability(MockFactory.UNIQUE_ID_A1, true);
    }

    @Test
    public void available_to_no_available_with_empty_search() {
        Mockito
                .when(this.entityManager.find(MockFactory.UNIQUE_ID_A1))
                .thenReturn(Optional.of(MockFactory.AVAILABLE_VEHICLE_A1));
        Mockito
                .when(this.provider.getVehicles())
                .thenReturn(this.getSet());

        this.service.checkAvailability();

        Mockito.verify(this.entityManager, Mockito.times(1)).find(MockFactory.UNIQUE_ID_A1);
        Mockito.verify(this.entityManager, Mockito.times(1)).updateAvailability(MockFactory.UNIQUE_ID_A1, false);
    }

    @Test
    public void available_to_no_available_with_others() {
        Mockito
                .when(this.entityManager.find(MockFactory.UNIQUE_ID_A1))
                .thenReturn(Optional.of(MockFactory.AVAILABLE_VEHICLE_A1));
        Mockito
                .when(this.provider.getVehicles())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_B2));

        this.service.checkAvailability();

        Mockito.verify(this.entityManager, Mockito.times(1)).find(MockFactory.UNIQUE_ID_A1);
        Mockito.verify(this.entityManager, Mockito.times(1)).updateAvailability(MockFactory.UNIQUE_ID_A1, false);
    }

    @Test
    public void error_database_only_rollback_that() {

    }

    @Test
    public void error_query_external_service() {

    }

    private Set<Vehicle> getSet(Vehicle... vehicles) {
        return new HashSet<>(Arrays.asList(vehicles));
    }

}
