package proftaak.service.marketservice.model;

//gebruik voor koop/verkoop energy
public class EnergyInOut {
    private String region;
    private double energyQuantity;

    public EnergyInOut(String region, double energyQuantity) {
        this.region = region;
        this.energyQuantity = energyQuantity;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getEnergyQuantity() {
        return energyQuantity;
    }

    public void setEnergyQuantity(double energyQuantity) {
        this.energyQuantity = energyQuantity;
    }
}
