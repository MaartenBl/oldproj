package com.service.sunservice.logic;

public enum Capacity {
    DRENTHE(528, "Hoogeveen"),
    FRIESLAND(585, "Leeuwarden"),
    GRONINGEN(576, "Eelde");

    private final int peak;
    private final String stationLocation;

    Capacity(int peak, String stationLocation) {
        this.peak = peak;
        this.stationLocation = stationLocation;
    }

    public int getPeak() {
        return peak;
    }
    public String getStationLocation() {
        return stationLocation;
    }
}
