package com.alvarolongueira.availablevehicles.controller.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAllAvailableResponse {

    public Set<VehicleResponse> vehicles;

}
