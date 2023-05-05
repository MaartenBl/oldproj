package com.service.meteoserverservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class Weather implements Serializable {
    @Id
    private int id;
    private int maxTemp;
    private int minTemp;
    private int windSpeed;
    private String note;
    @DateTimeFormat
    private Date date;

    public Weather(){}
    public Weather(Weather weather){
        this.id = weather.id;
        this.maxTemp = weather.maxTemp;
        this.minTemp = weather.minTemp;
        this.windSpeed = weather.windSpeed;
        this.note = weather.note;
        this.date = new Date(System.currentTimeMillis());
    }
    public Weather(int maxTemp, int minTemp, int windSpeed, String note) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.windSpeed = windSpeed;
        this.note = note;
        this.date = new Date(System.currentTimeMillis());
    }
    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
