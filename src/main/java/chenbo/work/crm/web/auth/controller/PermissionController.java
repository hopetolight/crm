package chenbo.work.crm.web.auth.controller;

import chenbo.work.crm.dao.settings.user.entity.Permission;
import chenbo.work.crm.dao.settings.user.entity.User;
import chenbo.work.crm.dao.settings.user.model.PermissionVO;
import chenbo.work.crm.service.user.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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


    @RequestMapping(value = "/addOrUpdate")
    public @ResponseBody Object add(Permission permission){

        Object principal = SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        BeanUtils.copyProperties(principal,user);
        permission.setTempstamp(new Date());
        permission.setOrderid(user.getId());
        if (permission.getPid()==null){
            permission.setPid(0L);
        }
        permission.setType(1);
        return permissionService.addPermission(permission);
    }
}
