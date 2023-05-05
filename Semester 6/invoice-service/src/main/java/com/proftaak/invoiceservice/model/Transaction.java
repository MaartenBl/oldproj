package com.proftaak.invoiceservice.model;

import org.decimal4j.util.DoubleRounder;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/
import java.io.Serializable;
import java.time.LocalDateTime;

//gebruik voor history
/*
@Entity
*/
public class Transaction /*implements Serializable*/ {
/*    private final double PRICE_PER_KWH = 0.22;

*//*    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)*//*
    private Integer id;
    private String region;
    private TransactionType transactionType;
    private double energyQuantity;
    private double totalPrice;
    private LocalDateTime dateTime;

    public Transaction(){};
    public Transaction(String region, TransactionType transactionType, double energyQuantity, LocalDateTime dateTime) {
        this.region = region;
        this.transactionType = transactionType;
        this.energyQuantity = energyQuantity;
        this.totalPrice = DoubleRounder.round(energyQuantity * PRICE_PER_KWH, 2);
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getEnergyQuantity() {
        return energyQuantity;
    }

    public void setEnergyQuantity(double energyQuantity) {
        this.energyQuantity = energyQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }*/
}

