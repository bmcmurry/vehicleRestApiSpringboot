package com.example.demo.vehicle;

import com.example.demo.maintenanceRecords.MaintenanceRecords;
import com.example.demo.owners.Owners;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Vehicle {
    @Id
    @SequenceGenerator(
            name = "vehicle_sequence",
            sequenceName = "vehicle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_sequence"
    )
    private Long id;

    private String make;
    private String model;
    private Integer year;
    private String color;
    private Integer mileage;
    @ManyToMany
    @JoinTable(
            name="owned_vehicles",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id")
    )
    private Set<Owners> vehiclesOwner = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "vehicle")
    private Set<MaintenanceRecords> vehiclesRecords = new HashSet<MaintenanceRecords>();

    public Vehicle() {

    }

    public Vehicle(Long id,
                   String make,
                   String model,
                   Integer year,
                   String color,
                   Integer mileage) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
    }

    public Vehicle(String make,
                   String model,
                   Integer year,
                   String color,
                   Integer mileage) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Set<Owners> getVehiclesOwner() {
        return vehiclesOwner;
    }

    public void setVehiclesOwner(Set<Owners> vehiclesOwner) {
        this.vehiclesOwner = vehiclesOwner;
    }


    public Set<MaintenanceRecords> getVehiclesRecords() {
        return vehiclesRecords;
    }

    public void setVehiclesRecords(Set<MaintenanceRecords> vehiclesRecords) {
        this.vehiclesRecords = vehiclesRecords;
    }
    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    public void assignOwner(Owners owners) {
        vehiclesOwner.add(owners);
    }

}
