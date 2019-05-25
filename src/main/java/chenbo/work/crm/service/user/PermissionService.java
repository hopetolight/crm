package chenbo.work.crm.service.user;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    /**
    * 通过角色ID查询可操作资源Id
    * @author; ChenBo
    * @datetime: 2019/5/22:0:19
    */
    List<String> queryPermissionsIdsByRoleIds(Set<String> roleIds);
}
