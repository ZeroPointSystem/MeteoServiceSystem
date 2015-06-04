package webserver.presentations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Zetro on 25.05.2015.
 */
@Controller
public class MeteoDataViewController {
    @RequestMapping(method = RequestMethod.GET, value = "index")
    public String getIndexPage() {
        return "index";
    }
}
