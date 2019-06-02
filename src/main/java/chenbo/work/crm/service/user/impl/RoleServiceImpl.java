package chenbo.work.crm.service.user.impl;

import chenbo.work.crm.dao.settings.user.entity.Role;
import chenbo.work.crm.dao.settings.user.entity.UserRoleRelation;
import chenbo.work.crm.dao.settings.user.mapper.RoleMapper;
import chenbo.work.crm.dao.settings.user.mapper.UserRoleRelationMapper;
import chenbo.work.crm.service.user.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;


    /**
     * 根据用户Id查询用户角色
     * @param userId
     * @author; ChenBo
     * @datetime: 2019/5/21:23:35
     */
    @Override
    public Set<Role> queryRoleByUserId(Long userId) {
        QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserRoleRelation::getUserid, userId);
        List<UserRoleRelation> userRoleRelations = userRoleRelationMapper.selectList(wrapper);
        if (userRoleRelations !=null){
            return userRoleRelations.stream().map(userRoleRelation -> roleMapper.selectByPrimaryKey(userRoleRelation.getRoleid()))
                    .collect(Collectors.toSet());
        }
        return new LinkedHashSet<>();
    }

    /**
     * 根据用户Id查询用户角色Id
     *
     * @param userId
     * @author; ChenBo
     * @datetime: 2019/5/21:23:58
     */
    @Override
    public Set<String> queryRoleIdsByUserId(Long userId) {
        QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserRoleRelation::getUserid, userId);
        List<UserRoleRelation> userRoleRelations = userRoleRelationMapper.selectList(wrapper);
        if (userRoleRelations !=null){
            return userRoleRelations.stream()
                    .map(userRoleRelation -> String.valueOf(roleMapper.selectByPrimaryKey(userRoleRelation.getRoleid()).getId()))
                    .collect(Collectors.toSet());
        }
        return new LinkedHashSet<>();
    }
}
