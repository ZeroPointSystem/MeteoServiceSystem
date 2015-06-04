package webserver.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Zetro on 11.05.2015.
 */
@Entity
@Table(name = "meteo_data")
public class MeteoData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "temperature")
    private float temperature;
    @Column(name = "pressure")
    private float pressure;
    @Column(name = "humidity")
    private float humidity;
    @Column(name = "time")
    private Timestamp time;

    // PRTS
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MeteoData{" +
                "humidity=" + humidity +
                ", id=" + id +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", time=" + time +
                '}';
    }
}
