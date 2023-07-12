package com.example.demo.vehicle;


import com.example.demo.maintenanceRecords.MaintenanceRecordsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VehicleService {

private final VehicleRepository vehicleRepository;
private final MaintenanceRecordsRepository maintenanceRecordsRepository;

    public VehicleService(VehicleRepository vehicleRepository, MaintenanceRecordsRepository maintenanceRecordsRepository) {
        this.vehicleRepository = vehicleRepository;
        this.maintenanceRecordsRepository = maintenanceRecordsRepository;
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public void addNewVehicles(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long vehicleId) {
        boolean exists = vehicleRepository.existsById(vehicleId);
        if (!exists)  {
            throw new IllegalStateException("owner with id " + vehicleId + " does not exist");
        }
        vehicleRepository.deleteById(vehicleId);
    }

    @Transactional
    public void updateVehicle(Long vehicleId,
                             String make,
                             String model,
                              Integer year,
                              String color,
                              Integer mileage) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalStateException(
                        "vehicle with id " + vehicleId + " does not exist"));

        if (make != null &&
                make.length() > 0 &&
                !Objects.equals(vehicle.getMake(), make)) {
            vehicle.setMake(make);
        }
        if (model != null &&
                model.length() > 0 &&
                !Objects.equals(vehicle.getModel(), model)) {
            vehicle.setModel(model);
        }
        if (year != null &&
                year > 0 &&
                !Objects.equals(vehicle.getYear(), year)) {
            vehicle.setYear(year);
        }
        if (color != null &&
                color.length() > 0 &&
                !Objects.equals(vehicle.getColor(), color)) {
            vehicle.setColor(color);
        }
        if (mileage != null &&
                mileage > 0 &&
                !Objects.equals(vehicle.getMileage(), mileage)) {
            vehicle.setMileage(mileage);
        }
    }

    @Transactional
    public Vehicle findVehicle(Long vehicleId) {
        boolean exists = vehicleRepository.existsById(vehicleId);
        if (!exists)  {
            throw new IllegalStateException("vehicle with id " + vehicleId + " does not exist");
        }
        Vehicle vehicleFound = (vehicleRepository.findById(vehicleId).get());
        return vehicleFound;
    }
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> getVehiclesByMake(String make) {
        return vehicleRepository.findVehicleByMake(make);
    }

}
