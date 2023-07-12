package com.example.demo.maintenanceRecords;

import com.example.demo.vehicle.Vehicle;
import com.example.demo.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/maintenanceRecords")
public class MaintenanceRecordsController {
    private final MaintenanceRecordsService maintenanceRecordsService;

    @Autowired
    public MaintenanceRecordsController(MaintenanceRecordsService maintenanceRecordsService) {
        this.maintenanceRecordsService = maintenanceRecordsService;

    }
    @GetMapping
    public List<MaintenanceRecords> getMaintenanceRecords() {
        return maintenanceRecordsService.getMaintenanceRecords();
    }

    @PostMapping
    public void registerNewMaintenanceRecords(@RequestBody MaintenanceRecords maintenanceRecords) {
        maintenanceRecordsService.addNewMaintenanceRecords(maintenanceRecords);

    }
    @DeleteMapping(path = "{maintenanceRecordsId}")
    public void deleteMaintenanceRecords(@PathVariable("maintenanceRecordsId") Long maintenanceRecordsId) {
        maintenanceRecordsService.deleteMaintenanceRecords(maintenanceRecordsId);
    }

    @PutMapping(path = "{maintenanceRecordsId}")
    public void updateMaintenanceRecords(
            @PathVariable("maintenanceRecordsId") Long maintenanceRecordsId,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer cost) {
        maintenanceRecordsService.updateMaintenanceRecords(maintenanceRecordsId, date, description, cost);
    }

}

