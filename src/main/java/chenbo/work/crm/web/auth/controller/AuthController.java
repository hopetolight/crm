package chenbo.work.crm.web.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {


    /**
    * 登录页面
    * @author; ChenBo
    * @datetime: 2019/5/25
    */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "auth/login";
    }

    /**
    *
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @RequestMapping("/unauth")
    public @ResponseBody Object unauth(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("message", "未登录");
        return map;
    }
    

    
    
}
