package com.daliycodebuffer.mq.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@RedisHash("Weather")
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
