package chenbo.work.crm.configuration.shiro;

import chenbo.work.crm.dao.settings.user.entity.User;
import chenbo.work.crm.service.user.PermissionService;
import chenbo.work.crm.service.user.RoleService;
import chenbo.work.crm.service.user.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @className ShiroRealm
 * @authtor ChenBo
 * @date 2019/5/27
 */
public class ShiroRealm extends AuthorizingRealm {


    @Autowired
   private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = new User();
        BeanUtils.copyProperties(principals.getPrimaryPrincipal(),user);
        Set<String> roleIds = roleService.queryRoleIdsByUserId(user.getId());
        authorizationInfo.addRoles(roleIds);
        List<String> permissions = permissionService.queryPermissionsIdsByRoleIds(roleIds);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        User user = userService.queryOneByName(username);
        if (Objects.isNull(user)) return null;
        if (StringUtils.equals(user.getLockstatus(),"0")) throw  new LockedAccountException();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                getName()
        );
        return authenticationInfo;
    }
}
