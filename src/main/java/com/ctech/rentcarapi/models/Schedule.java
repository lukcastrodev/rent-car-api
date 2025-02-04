package com.ctech.rentcarapi.models;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Schedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Temporal(TemporalType.TIMESTAMP)
    private Date rentInitialDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date rentExpirationDate;

    @CreationTimestamp(source = SourceType.DB)
    private Date createdAt;

    @UpdateTimestamp(source = SourceType.DB)
    private Date updatedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getRentInitialDate() {
        return rentInitialDate;
    }

    public void setRentInitialDate(Date rentInitialDate) {
        this.rentInitialDate = rentInitialDate;
    }

    public Date getRentExpirationDate() {
        return rentExpirationDate;
    }

    public void setRentExpirationDate(Date rentExpirationDate) {
        this.rentExpirationDate = rentExpirationDate;
    }
}
