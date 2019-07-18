package chenbo.work.crm.dao.settings.user.mapper;

import chenbo.work.crm.CrmApplicationTests;
import chenbo.work.crm.dao.settings.user.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Slf4j
public class UserMapperTest extends CrmApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionRelationMapper rolePermissionRelationMapper;


    @Test
    public void testSelect(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }


}
