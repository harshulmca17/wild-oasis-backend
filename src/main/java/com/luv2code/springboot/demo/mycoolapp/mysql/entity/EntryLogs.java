package com.luv2code.springboot.demo.mycoolapp.mysql.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "entryLogs")
public class EntryLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String referenceId;
    String remark;
    String toAccountNumber;
    String paymentReference;
    String narration;
    Double amount;

    String type ;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp created_at;


    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getId() {
        return id;
    }


    public Timestamp getCreated_at() {
        return created_at;
    }


    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public String getRemark() {
        return remark;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }
}
