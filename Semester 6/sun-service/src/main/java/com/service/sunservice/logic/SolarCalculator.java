package com.service.sunservice.logic;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.service.sunservice.model.ProductionAtTimeModel;

import java.util.ArrayList;
import java.util.List;

public class SolarCalculator {

    private static final int CONST_RADIATION = 990;

    /**
     * Calculates solar production in MW
     * @param radiation sun radiation to calculate production for, >= 0
     * @param peak peak production of selected area
     * @return Solar in mWh
     */
    public static int CalculateMWH(int radiation, int peak) {
        if (radiation >= 0){
            return radiation * peak / CONST_RADIATION;
        } else {
            throw new IllegalArgumentException();
        }
    }


    /**
     *
     * @param capacity
     * @param forecastArray
     * @return
     */
    public static List<ProductionAtTimeModel> calculateNext4Days(Capacity capacity, JsonArray forecastArray) {
        List<ProductionAtTimeModel> result = new ArrayList<>();

        for (JsonElement element: forecastArray) {
            JsonObject forecastObject = element.getAsJsonObject();

            int radiation = forecastObject.get("gr").getAsInt();

            result.add(new ProductionAtTimeModel(CalculateMWH(radiation ,capacity.getPeak()), forecastObject.get("time").getAsLong(), capacity.toString()));
        }
        return result;
    }
}
