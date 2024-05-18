package com.luv2code.springboot.demo.mycoolapp.mysql.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "Cabin")
public class Cabin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp created_at;

    String name;

    Integer maxCapacity;

    Integer regularPrice;

    Integer discount;

    String description;

    String image;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setRegularPrice(Integer regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Integer getRegularPrice() {
        return regularPrice;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    @Override
    public String toString() {
        return "CabinDto{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", name='" + name + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", regularPrice=" + regularPrice +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
