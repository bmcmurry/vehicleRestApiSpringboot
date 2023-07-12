package com.example.demo.maintenanceRecords;

import com.example.demo.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaintenanceRecordsRepository
        extends JpaRepository<MaintenanceRecords, Long> {
    @Query("Select s FROM MaintenanceRecords s WHERE s.date = ?1")
        // SELECT * FROM MaintenanceRecords WHERE date = ?
    Optional<MaintenanceRecords> findMaintenanceRecordsByDate(String date);

    Optional<MaintenanceRecords> findByDescription(String description);
}
