package com.luv2code.springboot.demo.mycoolapp.mysql.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "deposites")
public class Deposites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    String accountId;
    String type ;
    Double amount;



    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp created_at;

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getType() {
        return type;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
