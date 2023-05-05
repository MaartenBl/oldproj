package com.proftaak.invoiceservice.model;

import com.proftaak.invoiceservice.model.international.OfferResponse;
import lombok.Data;
import lombok.ToString;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "buy_offer_response_model")
public class BuyOfferResponseModel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String buyerName;
    private String offerId;
    private double amount;
    private double priceTotal;


    public BuyOfferResponseModel(String buyerName, String OfferID, double amount, double priceTotal) {
        this.buyerName = buyerName;
        this.offerId = OfferID;
        this.amount = amount;
        this.priceTotal = priceTotal;
    }

    public BuyOfferResponseModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getOffer() {
        return offerId;
    }

    public void setOffer(String offer) {
        this.offerId = offer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }
}
