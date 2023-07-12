package com.example.demo.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository
        extends JpaRepository<Vehicle, Long> {
    @Query("Select s FROM Vehicle s WHERE s.make = ?1")
        // SELECT * FROM Vehicle WHERE make = ?
    Optional<Vehicle> findVehicleByMake(String make);
}
