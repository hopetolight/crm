package chenbo.work.crm.dao.user.mapper;

import chenbo.work.crm.CrmApplicationTests;
import chenbo.work.crm.dao.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Slf4j
public class UserMapperTest extends CrmApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelect(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }

    @Test
    public void testInster(){
        User user = new User();
        user.setUsername("test");
        user.setLockstatus("1");
        user.setRealname("超级管理员");
        user.setStatus(1);
        user.setTimestamp(new Date());
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user);
    }
}
