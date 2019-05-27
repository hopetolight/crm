package chenbo.work.crm.service.user.impl;

import chenbo.work.crm.dao.user.entity.User;
import chenbo.work.crm.dao.user.mapper.UserMapper;
import chenbo.work.crm.service.user.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




/**
*
* @author; ChenBo
* @datetime: 2019/5/21:22:43
*/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 通过条件获取一个用户
     * @param user
     * @author; ChenBo
     * @datetime: 2019/5/21:22:45
     */
    @Override
    public User queryOneUser(User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.setEntity(user);
        return  userMapper.selectOne(userQueryWrapper);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @author; ChenBo
     * @datetime: 2019/5/27
     */
    @Override
    public User queryOneByName(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().eq(User::getUsername,username);
        return userMapper.selectOne(userQueryWrapper);
    }
}
