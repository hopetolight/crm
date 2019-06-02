package chenbo.work.crm.web.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 权限模块
 * @className PermissionController
 * @authtor ChenBo
 * @date 2019/5/30
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "auth/permission";
    }

    @RequestMapping(value = "/queryList")
    public @ResponseBody Object queryList(Object o){
        String json = "[{" +
                "\"id\":1," +
                "\"name\":\"C\"," +
                "\"size\":\"\"," +
                "\"date\":\"02/19/2010\"," +
                "\"children\":[{" +
                "\"id\":2," +
                "\"name\":\"Program Files\"," +
                "\"size\":\"120 MB\"," +
                "\"date\":\"03/20/2010\"," +
                "\"children\":[{" +
                "\"id\":21," +
                "\"name\":\"Java\"," +
                "\"size\":\"\"," +
                "\"date\":\"01/13/2010\"," +
                "\"state\":\"closed\"," +
                "\"children\":[{" +
                "\"id\":211," +
                "\"name\":\"ava.exe\"," +
                "\"size\":\"142 KB\"," +
                "\"date\":\"01/13/2010\"" +
                "},{" +
                "\"id\":212," +
                "\"name\":\"awt.dll\"," +
                "\"size\":\"5 KB\"," +
                "\"date\":\"01/13/2010\"" +
                "}]" +
                "},{" +
                "\"id\":22," +
                "\"name\":\"MySQL\"," +
                "\"size\":\"\"," +
                "\"date\":\"01/13/2010\"," +
                "\"state\":\"closed\"," +
                "\"children\":[{" +
                "\"id\":221," +
                "\"name\":\"my.ini\"," +
                "\"size\":\"10 KB\"," +
                "\"date\":\"02/26/2009\"" +
                "},{" +
                "\"id\":222," +
                "\"name\":\"my-huge.ini\"," +
                "\"size\":\"5 KB\"," +
                "\"date\":\"02/26/2009\"" +
                "},{" +
                "\"id\":223," +
                "\"name\":\"my-large.ini\"," +
                "\"size\":\"5 KB\"," +
                "\"date\":\"02/26/2009\"" +
                "}]" +
                "}]" +
                "},{" +
                "\"id\":3," +
                "\"name\":\"eclipse\"," +
                "\"size\":\"\"," +
                "\"date\":\"01/20/2010\"," +
                "\"children\":[{" +
                "\"id\":31," +
                "\"name\":\"eclipse.exe\"," +
                "\"size\":\"56 KB\"," +
                "\"date\":\"05/19/2009\"" +
                "},{" +
                "\"id\":32," +
                "\"name\":\"eclipse.ini\"," +
                "\"size\":\"1 KB\"," +
                "\"date\":\"04/20/2010\"" +
                "},{" +
                "\"id\":33," +
                "\"name\":\"notice.html\"," +
                "\"size\":\"7 KB\"," +
                "\"date\":\"03/17/2005\"" +
                "}]" +
                "}]" +
                "}]";
        return json;
    }
}
