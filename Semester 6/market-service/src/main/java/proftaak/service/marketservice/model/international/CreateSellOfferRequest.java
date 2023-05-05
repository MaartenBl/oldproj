package proftaak.service.marketservice.model.international;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.decimal4j.util.DoubleRounder;

@Data
@ToString
public class CreateSellOfferRequest {
    private final double PRICE_PER_KWH = 0.22;
    private double amount;
    private double price;

    public CreateSellOfferRequest(double amount) {
        this.amount = amount;
        this.price = DoubleRounder.round(amount * PRICE_PER_KWH, 2);
    }

    public CreateSellOfferRequest(){}
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
