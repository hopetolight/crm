package chenbo.work.crm.web.auth.controller;

import chenbo.work.crm.dao.settings.user.model.PermissionVO;
import chenbo.work.crm.service.user.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 权限模块
 * @className PermissionController
 * @authtor ChenBo
 * @date 2019/5/30
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;

    /** 权限首页 */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "auth/permission";
    }

    /**
    * 权限数据
    * @author; ChenBo
    * @datetime: 2019/6/2
    */
    @RequestMapping(value = "/queryList")
    public @ResponseBody Object queryList(){
       List<PermissionVO> permissionVOList =  permissionService.queryList();
       return permissionVOList;
    }



}
