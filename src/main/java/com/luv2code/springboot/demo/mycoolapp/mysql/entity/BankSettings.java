package com.luv2code.springboot.demo.mycoolapp.mysql.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "bankSettings")
public class BankSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    String accountId;
    String confg;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    

    public String getAccountId() {
        return accountId;
    }

    public Integer getId() {
        return id;
    }

    public String getConfg() {
        return confg;
    }

    public void setConfg(String confg) {
        this.confg = confg;
    }
}
