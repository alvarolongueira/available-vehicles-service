package com.alvarolongueira.availablevehicles.business.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.alvarolongueira.availablevehicles.business.CalculateAvailableVehiclesService;
import com.alvarolongueira.availablevehicles.domain.Vehicle;
import com.alvarolongueira.availablevehicles.exception.DatabaseServiceException;
import com.alvarolongueira.availablevehicles.exception.ProviderNotAvailableException;
import com.alvarolongueira.availablevehicles.mock.MockFactory;
import com.alvarolongueira.availablevehicles.provider.QueryAvailableVehicleProvider;
import com.alvarolongueira.availablevehicles.repository.VehicleEntityManager;

@RunWith(MockitoJUnitRunner.class)
public class CalculateAvailableVehiclesServiceActionTest {

    @Mock
    private VehicleEntityManager entityManager;

    @Mock
    private QueryAvailableVehicleProvider provider;

    @InjectMocks
    private CalculateAvailableVehiclesService service = new CalculateAvailableVehiclesServiceAction();

    @Before
    public void setUp() {

    }

    @Test
    public void no_exists_then_create_as_available() {
        //Repository
        Mockito
                .when(this.entityManager.find(MockFactory.UNIQUE_ID_A1))
                .thenReturn(Optional.empty());
        Mockito
                .when(this.entityManager.findAllAvailable())
                .thenReturn(this.getSet());
        //Provider
        Mockito
                .when(this.provider.getVehicles())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_A1));

        this.service.checkAvailability();

        Mockito.verify(this.entityManager, Mockito.times(1)).find(MockFactory.UNIQUE_ID_A1);
        Mockito.verify(this.entityManager, Mockito.times(1)).insert(MockFactory.AVAILABLE_VEHICLE_A1);
    }

    @Test
    public void no_available_to_available() {
        //Repository
        Mockito
                .when(this.entityManager.find(MockFactory.UNIQUE_ID_A1))
                .thenReturn(Optional.of(MockFactory.NO_AVAILABLE_VEHICLE_A1));
        Mockito
                .when(this.entityManager.findAllAvailable())
                .thenReturn(this.getSet(MockFactory.NO_AVAILABLE_VEHICLE_A1));
        //Provider
        Mockito
                .when(this.provider.getVehicles())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_A1));

        this.service.checkAvailability();

        Mockito.verify(this.entityManager, Mockito.times(1)).find(MockFactory.UNIQUE_ID_A1);
        Mockito.verify(this.entityManager, Mockito.times(1)).updateAvailability(MockFactory.UNIQUE_ID_A1, true);
    }

    @Test
    public void available_to_no_available_with_empty_search() {
        //Repository
        Mockito
                .when(this.entityManager.findAllAvailable())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_A1));
        //Provider
        Mockito
                .when(this.provider.getVehicles())
                .thenReturn(this.getSet());

        this.service.checkAvailability();

        Mockito.verify(this.entityManager, Mockito.times(1)).updateAvailability(MockFactory.UNIQUE_ID_A1, false);
    }

    @Test
    public void available_to_no_available_with_more_in_the_list() {
        //Repository
        Mockito
                .when(this.entityManager.findAllAvailable())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_A1));
        //Provider
        Mockito
                .when(this.provider.getVehicles())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_B2));

        this.service.checkAvailability();

        Mockito.verify(this.entityManager, Mockito.times(1)).updateAvailability(MockFactory.UNIQUE_ID_A1, false);
    }

    @Test
    public void already_available_do_nothing() {
        //Repository
        Mockito
                .when(this.entityManager.find(MockFactory.UNIQUE_ID_A1))
                .thenReturn(Optional.of(MockFactory.AVAILABLE_VEHICLE_A1));
        Mockito
                .when(this.entityManager.findAllAvailable())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_A1));      //Provider
        Mockito
                .when(this.provider.getVehicles())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_A1));

        this.service.checkAvailability();

        Mockito.verify(this.entityManager, Mockito.times(1)).find(MockFactory.UNIQUE_ID_A1);
        Mockito.verify(this.entityManager, Mockito.times(0)).insert(Mockito.any());
        Mockito.verify(this.entityManager, Mockito.times(0)).updateAvailability(Mockito.anyString(), Mockito.anyBoolean());
    }

    @Test
    public void error_database_only_rollback_first() {
        //Repository
        Mockito
                .when(this.entityManager.find(MockFactory.UNIQUE_ID_A1))
                .thenReturn(Optional.of(MockFactory.NO_AVAILABLE_VEHICLE_A1));
        Mockito
                .when(this.entityManager.findAllAvailable())
                .thenReturn(this.getSet(MockFactory.NO_AVAILABLE_VEHICLE_A1));
        Mockito
                .when(this.entityManager.find(MockFactory.UNIQUE_ID_B2))
                .thenReturn(Optional.of(MockFactory.NO_AVAILABLE_VEHICLE_B2));
        //Provider
        Mockito
                .when(this.provider.getVehicles())
                .thenReturn(this.getSet(MockFactory.AVAILABLE_VEHICLE_A1, MockFactory.AVAILABLE_VEHICLE_B2));

        Mockito
                .doThrow(new DatabaseServiceException(""))
                .when(this.entityManager).updateAvailability(MockFactory.UNIQUE_ID_A1, true);

        this.service.checkAvailability();

        Mockito.verify(this.entityManager, Mockito.times(1)).find(MockFactory.UNIQUE_ID_A1);
        Mockito.verify(this.entityManager, Mockito.times(1)).updateAvailability(MockFactory.UNIQUE_ID_A1, true);
        Mockito.verify(this.entityManager, Mockito.times(1)).find(MockFactory.UNIQUE_ID_B2);
        Mockito.verify(this.entityManager, Mockito.times(1)).updateAvailability(MockFactory.UNIQUE_ID_B2, true);
    }

    @Test(expected = ProviderNotAvailableException.class)
    public void error_query_external_service() {
        //Provider
        Mockito
                .when(this.provider.getVehicles())
                .thenThrow(new ProviderNotAvailableException(""));

        this.service.checkAvailability();
    }

    private Set<Vehicle> getSet(Vehicle... vehicles) {
        return new HashSet<>(Arrays.asList(vehicles));
    }

}
