package chenbo.work.crm.service.user;

import chenbo.work.crm.dao.settings.user.entity.Permission;
import chenbo.work.crm.dao.settings.user.model.PermissionVO;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    /**
    * 通过角色ID查询可操作资源Id
    * @author; ChenBo
    * @datetime: 2019/5/22:0:19
    */
    List<String> queryPermissionsIdsByRoleIds(Set<String> roleIds);

    List<PermissionVO> queryList();

    /**
    * 添加权限
    * @author; ChenBo
    * @datetime: 2019/6/2
    */
    Object addPermission(Permission permission);
}
