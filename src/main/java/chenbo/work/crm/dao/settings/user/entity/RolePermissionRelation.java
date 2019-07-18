package chenbo.work.crm.dao.settings.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_role_permission_relation")
public class RolePermissionRelation {

    @TableId
    private Long id;


    private Long roleId;


    private Long permissonId;


}