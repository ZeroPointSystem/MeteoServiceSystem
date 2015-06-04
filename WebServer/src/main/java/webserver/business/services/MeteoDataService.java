package webserver.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webserver.business.managers.MeteoDataManager;
import webserver.entities.MeteoData;
import webserver.entities.MeteoDataDTO;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
/**
 * Created by Zetro on 11.05.2015.
 */
@Service
public class MeteoDataService {
    private MeteoDataManager dataManager;
    List<MeteoData> meteoDataList;
    private MeteoDataDTO meteoDataDTO;

    public MeteoDataDTO getMeteoDataByDate(Timestamp date) {
        meteoDataList = dataManager.getMeteoDataByDate(date);
        /*for (MeteoData meteoData : meteoDataList) {
            System.out.println(meteoData);
        }*/
        meteoDataDTO = new MeteoDataDTO();
        meteoDataDTO.setAverageTemperature(getAverageTemperature());
        meteoDataDTO.setCurrentHumidity(getCurrentHumidity());
        meteoDataDTO.setCurrentPressure(getCurrentPressure());
        meteoDataDTO.setCurrentTemperature(getCurrentTemperature());
        meteoDataDTO.setMaxTemperature(getMaxTemperature());
        meteoDataDTO.setMinTemperature(getMinTemperature());
        meteoDataDTO.setTemperatureChart(getTemperatureChart());
        return meteoDataDTO;
    }

    private float getAverageTemperature() {
        float tempSumm = 0;
        for (MeteoData meteoData : meteoDataList) {
            tempSumm += meteoData.getTemperature();
        }
        return new Float(tempSumm/meteoDataList.size()).shortValue();
    }

    private float getMinTemperature() {
        float minTemperature = 1000.00F;
        for (MeteoData meteoData : meteoDataList) {
            if (meteoData.getTemperature() <= minTemperature) {
                minTemperature = meteoData.getTemperature();
            }
        }
        return minTemperature;
    }

    private float getMaxTemperature() {
        float maxTemperature = -1000.00F;
        for (MeteoData meteoData : meteoDataList) {
            if (meteoData.getTemperature() >= maxTemperature) {
                maxTemperature = meteoData.getTemperature();
            }
        }
        return maxTemperature;
    }

    private float getCurrentPressure() {
        return meteoDataList.get(meteoDataList.size() - 1).getPressure();
    }

    private float getCurrentTemperature() {
        return meteoDataList.get(meteoDataList.size() - 1).getTemperature();
    }

    private float getCurrentHumidity() {
        return meteoDataList.get(meteoDataList.size() - 1).getHumidity();
    }

    private Object[] getTemperatureChart() {
        Iterator<MeteoData> iterator = meteoDataList.iterator();
        Object[] chart = new Object[(meteoDataList.size()/5) + 1];
        float temperaturePerTime = 0;
        int timeCounter = 0;
        int counter = 0;
        while (iterator.hasNext()) {
            if (counter == 5) {
                counter = 0;
                timeCounter++;
                chart[timeCounter] = new Object[]{timeCounter, new Float(temperaturePerTime/5).floatValue()};
                temperaturePerTime = 0;
            } else {
                counter++;
                temperaturePerTime += iterator.next().getTemperature();
            }
        }
        return chart;
    }



    @Autowired
    public void setDataManager(MeteoDataManager dataManager) {
        this.dataManager = dataManager;
    }
}
