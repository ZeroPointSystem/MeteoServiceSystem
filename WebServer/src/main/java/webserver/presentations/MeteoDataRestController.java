package webserver.presentations;

import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webserver.business.services.MeteoDataService;
import webserver.entities.MeteoDataDTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Zetro on 11.05.2015.
 */
@RestController
public class MeteoDataRestController {
    private MeteoDataService meteoDataService;

    @RequestMapping(method = RequestMethod.POST, value = "index")
    private @ResponseBody MeteoDataDTO getMeteoData(@RequestBody Map date) {
        Timestamp timestamp = Timestamp.valueOf(String.valueOf(date.get("postData")) + " 00:00:00.0");// yyyy-mm-dd hh:mm:ss
        MeteoDataDTO meteoDataDTO = meteoDataService.getMeteoDataByDate(timestamp);
        return meteoDataDTO;
    }

    @Autowired
    public void setMeteoDataService(MeteoDataService meteoDataService) {
        this.meteoDataService = meteoDataService;
    }
}
