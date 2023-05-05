package com.daliycodebuffer.mq.repositories.interfaces;

import com.daliycodebuffer.mq.models.EnergyModel;
import com.daliycodebuffer.mq.models.Weather;

import java.util.Map;

public interface IEnergyRepository {
    void save(String energyMsg);
    Map<String, EnergyModel> findAll();
    void update(EnergyModel energy);
    void delete(String name);
    EnergyModel findByEnergy(int id);
}
