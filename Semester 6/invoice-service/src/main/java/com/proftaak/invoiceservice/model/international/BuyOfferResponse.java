package com.proftaak.invoiceservice.model.international;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class BuyOfferResponse {
    private String buyerName;
    private OfferResponse offer;
    private double amount;
    private double priceTotal;

    public BuyOfferResponse(String buyerName, OfferResponse offer, double amount, double priceTotal) {
        this.buyerName = buyerName;
        this.offer = offer;
        this.amount = amount;
        this.priceTotal = priceTotal;
    }

    public BuyOfferResponse() {

    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public OfferResponse getOffer() {
        return offer;
    }

    public void setOffer(OfferResponse offer) {
        this.offer = offer;
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
