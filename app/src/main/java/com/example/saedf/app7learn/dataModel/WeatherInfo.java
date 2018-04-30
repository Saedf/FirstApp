package com.example.saedf.app7learn.dataModel;

public class WeatherInfo {
private String WeatherMain;
private String WeatherDescription;
private float WeatherTemprature;
private float humidity;
private float windSpeed;
private float windDegree;
private float minTemprature;
private float maxTemprature;
private int pressure;
private String Name;

    public String getWeatherMain() {
        return WeatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        WeatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return WeatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        WeatherDescription = weatherDescription;
    }

    public float getWeatherTemprature() {
        return WeatherTemprature;
    }

    public void setWeatherTemprature(float weatherTemprature) {
        WeatherTemprature = weatherTemprature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(float windDegree) {
        this.windDegree = windDegree;
    }

    public float getMinTemprature() {
        return minTemprature;
    }

    public void setMinTemprature(float minTemprature) {
        this.minTemprature = minTemprature;
    }

    public float getMaxTemprature() {
        return maxTemprature;
    }

    public void setMaxTemprature(float maxTemprature) {
        this.maxTemprature = maxTemprature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
