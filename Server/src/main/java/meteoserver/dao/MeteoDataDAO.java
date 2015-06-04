package meteoserver.dao;

import meteoserver.entities.MeteoData;

/**
 * Created by Zetro on 11.05.2015.
 */
public interface MeteoDataDAO {
    boolean create(MeteoData data);
}
