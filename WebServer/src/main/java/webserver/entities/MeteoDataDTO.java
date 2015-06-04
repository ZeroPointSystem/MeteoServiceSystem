package webserver.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Zetro on 22.05.2015.
 */
public class MeteoDataDTO {

    private float currentTemperature;
    private float currentPressure;
    private float currentHumidity;
    private float maxTemperature;
    private float minTemperature;
    private float averageTemperature;
    private Object[] temperatureChart;

    // PRTS


    public Object[] getTemperatureChart() {
        return temperatureChart;
    }

    public void setTemperatureChart(Object[] temperatureChart) {
        this.temperatureChart = temperatureChart;
    }

    public float getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(float averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public float getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(float currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public float getCurrentPressure() {
        return currentPressure;
    }

    public void setCurrentPressure(float currentPressure) {
        this.currentPressure = currentPressure;
    }

    public float getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(float currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }
}
