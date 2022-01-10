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
public class ExternalVehicleResponse {

    public List<ExternalVehicle> vehicles;

}
