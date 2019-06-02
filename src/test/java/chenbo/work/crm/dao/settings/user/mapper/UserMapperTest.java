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

    @Test
    public void Role(){
        Role role = new Role();
        role.setCode("002");
        role.setName("root");
        role.setDescription("管理员");
        role.setTimestamp(new Date());
        roleMapper.insert(role);
        System.out.println(role);
    }

    @Test
    public void RoleUser(){
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setRoleid(1133752729395793921L);
        userRoleRelation.setUserid(1132373876040753152L);
        userRoleRelation.setStatus(1);
        userRoleRelationMapper.insert(userRoleRelation);
        userRoleRelation.setTimestapm(new Date());
    }

    @Test
    public void permission(){
        Permission permission = new Permission();
        permission.setName("用户首页");
        permission.setCode("/user/index");
        permission.setStatus(1);
        permission.setTempstamp(new Date());
        permissionMapper.insert(permission);
        System.out.println(permission);
    }

    @Test
    public void permissionRole(){
        RolePermissionRelation rolePermissionRelation = new RolePermissionRelation();
        rolePermissionRelation.setPermissionid(1133757612144463874L);
        rolePermissionRelation.setRoleid(1133752729395793921L);
        rolePermissionRelation.setStatus(1);
        rolePermissionRelation.setTimestamp(new Date());
        rolePermissionRelationMapper.insert(rolePermissionRelation);
    }
}
