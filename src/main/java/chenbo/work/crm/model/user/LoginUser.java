package chenbo.work.crm.model.user;

import chenbo.work.crm.dao.user.entity.Role;
import chenbo.work.crm.dao.user.entity.User;
import lombok.Data;

@Data
public class LoginUser extends User {

    /** 登录用户角色 */
    private Role role;

}
