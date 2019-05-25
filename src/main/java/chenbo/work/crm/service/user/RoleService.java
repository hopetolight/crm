package chenbo.work.crm.service.user;

import chenbo.work.crm.dao.user.entity.Role;

import java.util.Set;

/**
*
* @author; ChenBo
* @datetime: 2019/5/21:23:33
*/
public interface RoleService {

    /**
    * 根据用户Id查询用户角色
    * @author; ChenBo
    * @datetime: 2019/5/21:23:35
    */
    Set<Role> queryRoleByUserId(Long userId);

    /**
    *根据用户Id查询用户角色Id
    * @author; ChenBo
    * @datetime: 2019/5/21:23:58
    */
    Set<String> queryRoleIdsByUserId(Long id);
}
