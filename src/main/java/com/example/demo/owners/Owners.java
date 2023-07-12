package com.example.demo.owners;

import com.example.demo.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Owners {

    @Id
    @SequenceGenerator(
            name = "owners_sequence",
            sequenceName = "owners_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owners_sequence"
    )

    private Long id;
    private String name;
    private String address;
    private LocalDate dob;
    @Transient
    private Integer age;

    @JsonIgnore
    @ManyToMany(mappedBy = "vehiclesOwner")
    private Set<Vehicle> ownedVehicles = new HashSet<>();

    public Owners() {
    }

    public Owners(Long id,
                  String name,
                  String address,
                  LocalDate dob) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dob = dob;
    }

    public Owners(String name,
                  String address,
                  LocalDate dob) {
        this.name = name;
        this.address = address;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String owner) {
        this.name = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Vehicle> getOwnedVehicles() {
        return ownedVehicles;
    }
    @Override
    public String toString() {
        return "Owners{" +
                "id=" + id +
                ", owner='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
