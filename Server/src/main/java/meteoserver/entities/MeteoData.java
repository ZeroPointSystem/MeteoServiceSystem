package meteoserver.entities;

import java.sql.Timestamp;

/**
 * Created by Zetro on 11.05.2015.
 */
public class MeteoData {

    private float temperature;
    private float pressure;
    private float humidity;
    private Timestamp time;

    // PRST
    public float getTemperature() {
        return temperature;
    }
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    public float getPressure() {
        return pressure;
    }
    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
    public float getHumidity() {
        return humidity;
    }
    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
}
