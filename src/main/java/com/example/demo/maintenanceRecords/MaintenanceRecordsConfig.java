package com.example.demo.maintenanceRecords;

import com.example.demo.vehicle.Vehicle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class MaintenanceRecordsConfig {
    @Bean
    CommandLineRunner maintenancecommandLineRunner(
            MaintenanceRecordsRepository repository) {
        return args -> {
            MaintenanceRecords tires = new MaintenanceRecords(
                    LocalDate.of(2022,12,25),
                    "replaced my two front tires because the were old.",
                    40
            );
            MaintenanceRecords coils = new MaintenanceRecords(
                    LocalDate.of(2023,06,12),
                    "replaced all the engine coils and spark plugs.",
                    430
            );
            MaintenanceRecords oilChange = new MaintenanceRecords(
                    LocalDate.of(2023,05,8),
                    "got a fully synthetic oil change good for 5,000 miles at 85,000 miles.",
                    80
            );
            MaintenanceRecords carwash = new MaintenanceRecords(
                    LocalDate.of(2023,07,04),
                    "got a carwash and cleaned out the dog hair.",
                    10
            );


            repository.saveAll(
                    List.of(tires, coils, oilChange, carwash)
            );
        };
    }
}
