package com.example.demo.maintenanceRecords;

import com.example.demo.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
public class MaintenanceRecords {
    @Id
    @SequenceGenerator(
            name = "maintenance_sequence",
            sequenceName = "maintenance_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "maintenance_sequence"
    )

    private Long id;
    private LocalDate date;
    private String description;
    private Integer cost;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehiclesRecords", referencedColumnName = "id")
    private Vehicle vehicle;

    public MaintenanceRecords() {

    }

    public MaintenanceRecords(Long id,
                              LocalDate date,
                              String description,
                              Integer cost
                              ) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    public MaintenanceRecords(LocalDate date,
                              String description,
                              Integer cost
    ) {
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }


    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return "MaintenanceRecords{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
