# available-vehicles-service

## Why
We need to develop a microservice that checks every 60 seconds all the available vehicles in a zone.
To get these vehicles, we use and external api

## How
* Run class AvailableVehiclesApplication

This starts the application and check every minute to the external api.
It stores in memory database all these vehicles. No deletion in case they become available later.
There are two api's to see what's in the database:

* @GetMapping (path = "/available"): find all availableVehicles
* @GetMapping (path = "/available/{vehicleId}"): find vehicle by id, whatever its availability

## Where
* AvailableVehiclesApplicationTest: chech contexts
* CalculateAvailableVehiclesServiceActionTest: checks main business service
* AvailableVehiclesControllerTest: checks rest api

## What
Java classes:
* ScheduleService: executes the main service every 60 seconds
* CalculateAvailableVehiclesService: main service with the logic to calculate which vehicles are available
* QueryAvailableVehicleProvider: to invoke external api
* AvailableVehiclesController: to receive rest petitions

Packages:
* business: the main logic of the service
* controller: Receive the requests. We have to subpackages to define the objects in/out the service
* domain: main pojos in this service
* exception: all defined errors and its handler
* provider: external service api
* repository: logic to persist information. In this case is in memory. There are entity objects to separate from domain objects.

## Who
* **Alvaro Longueira** - [alvarolongueira](https://github.com/alvarolongueira)


