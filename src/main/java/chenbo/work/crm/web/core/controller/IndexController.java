package chenbo.work.crm.web.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/","/index","/login"})
    public String index(){
        return "auth/login";
    }
}
