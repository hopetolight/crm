package chenbo.work.crm.web.auth.controller;

import chenbo.work.crm.dao.user.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    
    /**
    *
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user){
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try{
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("user",user);

        }catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }
    
    
}
