package com.service.nuclearservice.model;


public class EnergyModel {
    public EnergyModel(String month, int avgValue, String source) {
        this.month = month;
        this.avgValue = avgValue;
        this.source = source;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getAvgValue() {
        return avgValue;
    }

    public void setAvgValue(int avgValue) {
        this.avgValue = avgValue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private String month;
    private int avgValue;
    private String source;

    @Override
    public String toString(){
        return "EnergyModel{" +
                ", month= " + month + '\'' +
                ", source= " + source + '\'' +
                ", avgValue= " + avgValue + '\'' +
                "}";
    }
}
