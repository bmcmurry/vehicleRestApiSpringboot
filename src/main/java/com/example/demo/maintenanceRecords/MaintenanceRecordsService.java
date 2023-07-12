package com.example.demo.maintenanceRecords;

import com.example.demo.vehicle.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MaintenanceRecordsService {


        private final MaintenanceRecordsRepository maintenanceRecordsRepository;


        public MaintenanceRecordsService(MaintenanceRecordsRepository maintenanceRecordsRepository) {
            this.maintenanceRecordsRepository = maintenanceRecordsRepository;
        }

        public List<MaintenanceRecords> getMaintenanceRecords() {
            return maintenanceRecordsRepository.findAll();
        }

    public void addNewMaintenanceRecords(MaintenanceRecords maintenanceRecords) {
        String description = maintenanceRecords.getDescription();

        Optional<MaintenanceRecords> existingRecord = maintenanceRecordsRepository.findByDescription(description);
        if (existingRecord.isPresent()) {
            throw new IllegalStateException("A maintenance record with the same description already exists");
        }
        maintenanceRecordsRepository.save(maintenanceRecords);
    }


    public void deleteMaintenanceRecords(Long maintenanceRecordsId) {
        boolean exists = maintenanceRecordsRepository.existsById(maintenanceRecordsId);
        if (!exists)  {
            throw new IllegalStateException("maintenance records with id " + maintenanceRecordsId + " does not exist");
        }
        maintenanceRecordsRepository.deleteById(maintenanceRecordsId);
    }

    @Transactional
    public void updateMaintenanceRecords(Long maintenanceRecordsId, LocalDate date, String description, Integer cost) {
        MaintenanceRecords maintenanceRecords = maintenanceRecordsRepository.findById(maintenanceRecordsId)
                .orElseThrow(() -> new IllegalStateException(
                        "maintenance records with id " + maintenanceRecordsId + " does not exist"));

        if (date != null &&
                date.lengthOfYear() > 0 &&
                !Objects.equals(maintenanceRecords.getDate(), date)) {
            maintenanceRecords.setDate(date);
        }
        if (description != null &&
                description.length() > 0 &&
                !Objects.equals(maintenanceRecords.getDescription(), description)) {
            maintenanceRecords.setDescription(description);
        }
        if (cost != null &&
                cost > 0 &&
                !Objects.equals(maintenanceRecords.getCost(), cost)) {
            maintenanceRecords.setCost(cost);
        }
    }

    @Transactional
    public MaintenanceRecords findMaintenanceRecords(Long maintenanceId) {
        boolean exists = maintenanceRecordsRepository.existsById(maintenanceId);
        if (!exists)  {
            throw new IllegalStateException("maintenance record with id " + maintenanceId + " does not exist");
        }
        MaintenanceRecords recordsFound = (maintenanceRecordsRepository.findById(maintenanceId).get());
        return recordsFound;
    }
    public MaintenanceRecords saveRecords(MaintenanceRecords maintenanceRecords) {
            System.out.println("saved worked");
        return maintenanceRecordsRepository.save(maintenanceRecords);
    }
}