package com.luv2code.springboot.demo.mycoolapp.mysql.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer cabinId;
    Integer guestId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp created_at;
    Timestamp startDate;
    Timestamp endDate;

    Integer numNights;
    Integer numGuests;

    Float cabinPrice;

    Float extrasPrice;

    Float totalPrice;

    String status;

    Boolean hasBreakfast;
    String observations;

    Boolean isPaid;

    public Float getExtrasPrice() {
        return extrasPrice;
    }

    public void setExtrasPrice(Float extrasPrice) {
        this.extrasPrice = extrasPrice;
    }


    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExtraPrice(Float extraPrice) {
        this.extrasPrice = extraPrice;
    }

    public void setHasBreakfast(Boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public void setNumGuests(Integer numGuests) {
        this.numGuests = numGuests;
    }

    public void setNumNights(Integer numNights) {
        this.numNights = numNights;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCabinPrice(Float cabinPrice) {
        this.cabinPrice = cabinPrice;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getHasBreakfast() {
        return hasBreakfast;
    }

    public Float getCabinPrice() {
        return cabinPrice;
    }

    public Float getExtraPrice() {
        return extrasPrice;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public Integer getNumGuests() {
        return numGuests;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public String getObservations() {
        return observations;
    }

    public Integer getNumNights() {
        return numNights;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public void setCabinId(Integer cabinId) {
        this.cabinId = cabinId;
    }

    public Integer getCabinId() {
        return cabinId;
    }

    public Integer getGuestId() {
        return guestId;
    }

    @Override
    public String toString() {
        return "BookingsDto{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numNights=" + numNights +
                ", numGuests=" + numGuests +
                ", cabinPrice=" + cabinPrice +
                ", extraPrice=" + extrasPrice +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", hasBreakfast=" + hasBreakfast +
                ", observations='" + observations + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }
}
