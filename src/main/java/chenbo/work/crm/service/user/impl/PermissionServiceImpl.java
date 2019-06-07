package chenbo.work.crm.service.user.impl;

import chenbo.work.crm.dao.settings.user.entity.Permission;
import chenbo.work.crm.dao.settings.user.entity.RolePermissionRelation;
import chenbo.work.crm.dao.settings.user.mapper.PermissionMapper;
import chenbo.work.crm.dao.settings.user.mapper.RolePermissionRelationMapper;
import chenbo.work.crm.dao.settings.user.model.PermissionVO;
import chenbo.work.crm.service.user.PermissionService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
//                permissionIds.addAll( rolePermissionRelationList.stream()
//////                        .map(rolePermissionRelation -> permissionMapper.selectByPrimaryKey(rolePermissionRelation.getPermissionid()).getCode())
//////                        .collect(Collectors.toList()));
            }
        });
        return permissionIds;
    }

    /**
    *
    * @author; ChenBo
    * @datetime: 2019/6/2
    */
    @Override
    public List<PermissionVO> queryList() {
        List<Permission> permissions = permissionMapper.selectList(null);
        Map<Long, Permission> permissionMap = permissions.stream().collect(Collectors.toMap(Permission::getId, v -> v));
        Map<Long, List<Long>> childrenIdList = permissions.stream().
                collect(Collectors.groupingBy(Permission::getPid, Collectors.mapping(Permission::getId, Collectors.toList())));

        List<PermissionVO> permissionVOList =new ArrayList<>();
        List<Long> childrens = childrenIdList.get(0L);
        if (childrens == null || childrens.isEmpty()) return permissionVOList;
        childrens.forEach(o-> permissionVOList.add(CreatPermission(o, permissionMap, childrenIdList)));

        return permissionVOList;
    }

    /**
    *
    * @author; ChenBo
    * @datetime: 2019/6/2
    */
    private PermissionVO CreatPermission(Long id,Map<Long, Permission> permissionMap,Map<Long, List<Long>> childrenIdList ){
        PermissionVO permissionVO = new PermissionVO();
        Permission permission = permissionMap.get(id);
        BeanUtils.copyProperties(permission,permissionVO);
        permissionVO.setChildren(new ArrayList<>());

        List<Long> childrens = childrenIdList.get(id);
        if (childrens == null || childrens.isEmpty()) return permissionVO;
        childrens.forEach(childrenId ->  permissionVO.getChildren().add(CreatPermission(childrenId, permissionMap, childrenIdList)));

        return permissionVO;

    }


    /**
     * 添加权限
     *
     * @param permission
     * @author; ChenBo
     * @datetime: 2019/6/2
     */
    @Override
    public Object addPermission(Permission permission) {
        int num;
        JSONObject jsonObject = new JSONObject();
        if (permission.getId()!=null){
            num = permissionMapper.updateById(permission);
        }else {
            num = permissionMapper.insert(permission);
        }

        if (num>0){
            jsonObject.put("code",200);
            jsonObject.put("data",permission);
        }else {
            jsonObject.put("code",-1);
            jsonObject.put("message","服务器错误");
        }
        return  jsonObject;
    }
}
