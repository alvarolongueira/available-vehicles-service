package com.alvarolongueira.availablevehicles.controller.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAllAvailableResponse {

    Set<VehicleResponse> vehicles;

}
