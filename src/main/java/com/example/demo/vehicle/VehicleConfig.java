package com.example.demo.vehicle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class VehicleConfig {

    @Bean
    CommandLineRunner vehiclecommandLineRunner(
            VehicleRepository repository) {
        return args -> {
            Vehicle mazda = new Vehicle(
                    "Mazda",
                    "CX-5",
                    2015,
                    "Silver",
                    89500
            );
            Vehicle honda = new Vehicle(
                    "Honda",
                    "Civic",
                    2005,
                    "Red",
                    110000
            );
            Vehicle toyota = new Vehicle(
                    "Toyota",
                    "Corolla",
                    1990,
                    "Gold",
                    250000
            );
            Vehicle chevy = new Vehicle(
                    "Chevy",
                    "Corvette",
                    1950,
                    "Yellow",
                    25000
            );

            repository.saveAll(
                    List.of(mazda, honda, toyota, chevy)
            );
        };
    }
}
