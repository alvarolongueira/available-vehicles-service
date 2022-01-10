package com.alvarolongueira.availablevehicles.controller;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.alvarolongueira.availablevehicles.controller.response.FindAllAvailableResponse;
import com.alvarolongueira.availablevehicles.controller.response.VehicleResponse;
import com.alvarolongueira.availablevehicles.mock.MockFactory;
import com.alvarolongueira.availablevehicles.repository.database.VehicleEntityRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class AvailableVehiclesControllerTest {

    private final String MAIN_URL = "/available/";

    @Autowired
    private AvailableVehiclesController controller;

    @Autowired
    private VehicleEntityRepository repository;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() {
        if (this.repository.existsById(MockFactory.UNIQUE_ID_A1)) {
            this.repository.deleteById(MockFactory.UNIQUE_ID_A1);
        }
        if (this.repository.existsById(MockFactory.UNIQUE_ID_B2)) {
            this.repository.deleteById(MockFactory.UNIQUE_ID_B2);
        }
        if (this.repository.existsById(MockFactory.UNIQUE_ID_C3)) {
            this.repository.deleteById(MockFactory.UNIQUE_ID_C3);
        }

        this.repository.save(MockFactory.ENTITY_AVAILABLE_A1);
        this.repository.save(MockFactory.ENTITY_NO_AVAILABLE_B2);
        this.repository.save(MockFactory.ENTITY_AVAILABLE_C3);
    }

    @Test
    public void find_two_vehicles() throws Exception {
        //check context
        Assert.assertNotNull(this.controller);
        ResponseEntity<FindAllAvailableResponse> responseEntityAll = this.template.getForEntity(this.MAIN_URL, FindAllAvailableResponse.class);

        Assert.assertEquals(HttpStatus.OK, responseEntityAll.getStatusCode());

        //find available
        FindAllAvailableResponse response = responseEntityAll.getBody();
        Assert.assertEquals(2, response.getVehicles().size());
        boolean existsA1 = response.getVehicles().stream().anyMatch(value -> MockFactory.UNIQUE_ID_A1.equals(value.getUniqueId()));
        boolean existsC3 = response.getVehicles().stream().anyMatch(value -> MockFactory.UNIQUE_ID_C3.equals(value.getUniqueId()));

        Assert.assertTrue(existsA1);
        Assert.assertTrue(existsC3);

        //find no available
        ResponseEntity<VehicleResponse> responseEntity = this.template.getForEntity(this.MAIN_URL + MockFactory.UNIQUE_ID_C3, VehicleResponse.class);
        VehicleResponse vehicle = responseEntity.getBody();
        Assert.assertEquals(MockFactory.UNIQUE_ID_C3, vehicle.getUniqueId());
    }

}
