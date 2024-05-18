package com.luv2code.springboot.demo.mycoolapp.mysql.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp created_at;

    Integer minBookingLength;
    Integer maxBookingLength;
    Integer maxGuestsPerBooking;

    Float breakFastPrice;

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBreakFastPrice(Float breakFastPrice) {
        this.breakFastPrice = breakFastPrice;
    }

    public void setMaxBookingLength(Integer maxBookinLength) {
        this.maxBookingLength = maxBookinLength;
    }

    public void setMaxGuestsPerBooking(Integer maxGuestsPerBooking) {
        this.maxGuestsPerBooking = maxGuestsPerBooking;
    }

    public void setMinBookingLength(Integer minBookinLength) {
        this.minBookingLength = minBookinLength;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Integer getId() {
        return id;
    }

    public Float getBreakFastPrice() {
        return breakFastPrice;
    }

    public Integer getMaxBookingLength() {
        return maxBookingLength;
    }

    public Integer getMaxGuestsPerBooking() {
        return maxGuestsPerBooking;
    }

    public Integer getMinBookingLength() {
        return minBookingLength;
    }

    @Override
    public String toString() {
        return "SettingsDto{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", minBookinLength=" + minBookingLength +
                ", maxBookinLength=" + maxBookingLength +
                ", maxGuestsPerBooking=" + maxGuestsPerBooking +
                ", breakFastPrice=" + breakFastPrice +
                '}';
    }
}
