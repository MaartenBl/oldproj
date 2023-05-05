package proftaak.service.marketservice.model;

import java.io.Serializable;

public class BuyOfferResponseModel implements Serializable {
    private Integer id;
    private String buyerName;
    private String OfferID;
    private double amount;
    private double priceTotal;


    public BuyOfferResponseModel(String buyerName, String OfferID, double amount, double priceTotal) {
        this.buyerName = buyerName;
        this.OfferID = OfferID;
        this.amount = amount;
        this.priceTotal = priceTotal;
    }

    public BuyOfferResponseModel() {

    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getOffer() {
        return OfferID;
    }

    public void setOffer(String offer) {
        this.OfferID = offer;
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
