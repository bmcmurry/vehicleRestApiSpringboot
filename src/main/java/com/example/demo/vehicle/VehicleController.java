package com.example.demo.vehicle;

import com.example.demo.maintenanceRecords.MaintenanceRecords;
import com.example.demo.maintenanceRecords.MaintenanceRecordsService;
import com.example.demo.owners.Owners;
import com.example.demo.owners.OwnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final OwnersService ownersService;
    private final MaintenanceRecordsService maintenanceRecordsService;


    @Autowired
    public VehicleController(VehicleService vehicleService,
                             OwnersService ownersService,
                             MaintenanceRecordsService maintenanceRecordsService) {
        this.vehicleService = vehicleService;
        this.ownersService = ownersService;
        this.maintenanceRecordsService = maintenanceRecordsService;
    }

    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    @GetMapping(path = "{make}")
    public Optional<Vehicle> getVehiclesByMake(@PathVariable("make") String make) {

        return vehicleService.getVehiclesByMake(make);
    }

    @PostMapping
    public void registerNewVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addNewVehicles(vehicle);

    }
    @DeleteMapping(path = "{vehicleId}")
    public void deleteVehicle(@PathVariable("vehicleId") Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }

    @PutMapping(path = "{vehicleId}")
    public void updateVehicle(
            @PathVariable("vehicleId") Long vehicleId,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer mileage) {
        vehicleService.updateVehicle(vehicleId, make, model, year, color, mileage);
    }


    @PutMapping("/{vehicleId}/owners/{ownersId}")
    Vehicle addOwnerToVehicle(
            @PathVariable Long vehicleId,
            @PathVariable Long ownersId
    ) {
        Vehicle vehicle = vehicleService.findVehicle(vehicleId);
        Owners owners = ownersService.findOwners(ownersId).get();
        vehicle.assignOwner(owners);
        return vehicleService.saveVehicle(vehicle);
    }
    @PutMapping("/{vehicleId}/maintenanceRecords/{maintenanceId}")
    MaintenanceRecords addMaintenanceRecordToVehicle(
            @PathVariable Long vehicleId,
            @PathVariable Long maintenanceId
    ) {
        MaintenanceRecords maintenanceRecords = maintenanceRecordsService.findMaintenanceRecords(maintenanceId);
        Vehicle vehicle = vehicleService.findVehicle(vehicleId);
        maintenanceRecords.setVehicle(vehicle);
        return maintenanceRecordsService.saveRecords(maintenanceRecords);
    }
}
