package com.luv2code.springboot.demo.mycoolapp.mysql.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp created_at;

    String fullName;
    String email;
    String nationality;
    String national;
    String countryFlag;

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Integer getId() {
        return id;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNational() {
        return national;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "GuestDto{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", nationality='" + nationality + '\'' +
                ", national='" + national + '\'' +
                ", countryFlag='" + countryFlag + '\'' +
                '}';
    }
}
