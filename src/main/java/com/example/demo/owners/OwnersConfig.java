package com.example.demo.owners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class OwnersConfig {

    @Bean
    CommandLineRunner ownerscommandLineRunner(
            OwnersRepository repository) {
        return args -> {
            Owners bryan = new Owners(
                    "Bryan",
                    "123 West Oxford Loop",
                    LocalDate.of(1991, 05, 22)
            );
            Owners alex = new Owners(
                    "Alex",
                    "100 main street",
                    LocalDate.of(1995, 06, 12)
            );
            Owners tim = new Owners(
                    "Tim",
                    "1 Martin Luther King street",
                    LocalDate.of(2000, 10, 4)
            );
            Owners randy = new Owners(
                    "Randy",
                    "777 Money Circle",
                    LocalDate.of(1950, 8, 1)
            );
            repository.saveAll(
                    List.of(bryan, alex, tim, randy)
            );
        };
    }
}
