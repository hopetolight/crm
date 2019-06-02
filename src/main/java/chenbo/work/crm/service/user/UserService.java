package chenbo.work.crm.service.user;

import chenbo.work.crm.dao.settings.user.entity.User;

public interface UserService {

    /**
    * 通过条件获取一个用户
    * @author; ChenBo
    * @datetime: 2019/5/21:22:45
    */
    User queryOneUser(User user);

    /**
    * 根据用户名查找用户
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    User queryOneByName(String username);
}
