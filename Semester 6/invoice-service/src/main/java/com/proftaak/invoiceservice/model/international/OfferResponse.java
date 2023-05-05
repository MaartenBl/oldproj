package com.proftaak.invoiceservice.model.international;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "offer_response")
public class OfferResponse  implements Serializable {

    @Id
    private String offerId;
    private String seller;
    private double amount;
    private double amountTotal;
    private double price;

    public OfferResponse(String id, String seller, double amount, double amountTotal, double price) {
        this.offerId = id;
        this.seller = seller;
        this.amount = amount;
        this.amountTotal = amountTotal;
        this.price = price;
    }

    public String getId() {
        return offerId;
    }

    public void setId(String id) {
        this.offerId = id;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(double amountTotal) {
        this.amountTotal = amountTotal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
