package chenbo.work.crm.web.settings.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户管理
 * @className UserController
 * @authtor ChenBo
 * @date 2019/5/28
 */
@Controller
@RequestMapping("/setting/user")
public class UserController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "settings/user/index";
    }
}
