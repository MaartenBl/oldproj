package com.service.sunservice.model;

public class ProductionAtTimeModel {
    private int mWh;
    private long timestamp;
    private String province;

    public ProductionAtTimeModel(int mWh, long timestamp, String province) {
        this.mWh = mWh;
        this.timestamp = timestamp;
        this.province = province;
    }

    public int getmWh() {
        return mWh;
    }

    public void setmWh(int mWh) {
        this.mWh = mWh;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
