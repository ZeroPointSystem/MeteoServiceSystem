package webserver.business.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import webserver.entities.MeteoData;
import javax.persistence.*;
import javax.transaction.*;
import java.sql.Timestamp;
import java.util.List;
/**
 * Created by Zetro on 11.05.2015.
 */
@Repository
public class MeteoDataManager {
    private UserTransaction userTransaction;
    private EntityManager entityManager;

    public List<MeteoData> getMeteoDataByDate(Timestamp date) {
        List<MeteoData> resultList;
        try {
            userTransaction.begin();
            String sql = "SELECT * FROM meteo_data WHERE time >= :meteo_date ORDER BY id ASC";
            Query nativeQuery = entityManager.createNativeQuery(sql, MeteoData.class);
            nativeQuery.setParameter("meteo_date", date);
            resultList = nativeQuery.getResultList();
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resultList;
    }

    @Autowired
    private void setUserTransaction(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }

    @Autowired
    private void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
