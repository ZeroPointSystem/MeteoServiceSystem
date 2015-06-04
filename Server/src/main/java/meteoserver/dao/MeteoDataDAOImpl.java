package meteoserver.dao;

import meteoserver.entities.MeteoData;
import meteoserver.utils.DataBaseManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Zetro on 11.05.2015.
 */
public class MeteoDataDAOImpl implements MeteoDataDAO {
    private DataBaseManager dataBaseManager;
    public MeteoDataDAOImpl(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }
    @Override
    public boolean create(MeteoData data) {
        try {
            PreparedStatement preparedStatement = dataBaseManager.getConnection().prepareStatement("INSERT INTO meteo_data (temperature, pressure, humidity, time)  VALUES (?,?,?,?)");
            preparedStatement.setFloat(1, data.getTemperature());
            preparedStatement.setFloat(2, data.getPressure());
            preparedStatement.setFloat(3, data.getHumidity());
            preparedStatement.setTimestamp(4, data.getTime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
