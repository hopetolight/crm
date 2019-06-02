package chenbo.work.crm.service.user.impl;

import chenbo.work.crm.dao.settings.user.entity.RolePermissionRelation;
import chenbo.work.crm.dao.settings.user.mapper.PermissionMapper;
import chenbo.work.crm.dao.settings.user.mapper.RolePermissionRelationMapper;
import chenbo.work.crm.service.user.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RolePermissionRelationMapper rolePermissionRelationMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 通过角色ID查询可操作资源Id
     * @param roleIds
     * @author; ChenBo
     * @datetime: 2019/5/22:0:19
     */
    @Override
    public List<String> queryPermissionsIdsByRoleIds(Set<String> roleIds) {
        if (roleIds.isEmpty()) return new ArrayList<>();
        ArrayList<String> permissionIds = new ArrayList<>();
        roleIds.forEach(roleId ->{
            QueryWrapper<RolePermissionRelation> rolePermissionRelationQueryWrapper = new QueryWrapper<>();
            rolePermissionRelationQueryWrapper.lambda().eq(RolePermissionRelation::getRoleid,roleId);
            List<RolePermissionRelation> rolePermissionRelationList = rolePermissionRelationMapper.selectList(rolePermissionRelationQueryWrapper);
            if(!rolePermissionRelationList.isEmpty()){
                permissionIds.addAll( rolePermissionRelationList.stream()
                        .map(rolePermissionRelation -> permissionMapper.selectByPrimaryKey(rolePermissionRelation.getPermissionid()).getCode())
                        .collect(Collectors.toList()));
            }
        });
        return permissionIds;
    }
}
