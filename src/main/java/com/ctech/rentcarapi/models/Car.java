package com.ctech.rentcarapi.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cars")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Length(min = 3, max = 100)
    private String brand;
    
    @NotNull
    @Length(min = 3, max = 100)
    private String model;
    
    @NotNull
    @Length(max = 4)
    private String year;

    @NotNull
    private BigDecimal fuel;

    @NotNull
    @Length(max = 10)
    private String condition;

    @Temporal(TemporalType.TIMESTAMP)
    private Date rentDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    private Boolean isRented = false;

    @ManyToMany(mappedBy = "cars", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return this.year;
    }

    public void setFuel(BigDecimal fuel) {
        this.fuel = fuel;
    }

    public BigDecimal getFuel() {
        return this.fuel;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getRentDate() {
        return this.rentDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setRented(Boolean isRented) {
        this.isRented = isRented;
    }

    public Boolean isRented() {
        return this.isRented;
    }
}
