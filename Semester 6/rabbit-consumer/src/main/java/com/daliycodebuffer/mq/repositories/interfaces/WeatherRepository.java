package com.daliycodebuffer.mq.repositories.interfaces;

import com.daliycodebuffer.mq.models.Weather;

import java.util.List;
import java.util.Map;

public interface WeatherRepository {
    void save(String weatherMsg);
    Map<String, Weather> findAll();
    void update(Weather weather);
    void delete(String name);
    Weather findByTemp(int minTemp);
}
