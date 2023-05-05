package proftaak.service.marketservice.model;

import org.decimal4j.util.DoubleRounder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//gebruik voor history
public class Transaction implements Serializable {
    private final double PRICE_PER_KWH = 0.22;

    private Integer id;
    private String region;
    private TransactionType transactionType;
    private double energyQuantity;
    private double totalPrice;
    private String dateTime;

    public Transaction(){};
  public Transaction(String region, TransactionType transactionType, double energyQuantity) {
        this.region = region;
        this.transactionType = transactionType;
        this.energyQuantity = energyQuantity;

        this.totalPrice = DoubleRounder.round(energyQuantity * PRICE_PER_KWH, 2);
        this.dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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

    public String getDateTime() {
        return dateTime;
    }
}
