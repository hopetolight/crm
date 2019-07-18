package chenbo.work.crm.dao.settings.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_user_role_relation")
public class UserRoleRelation {

    @TableId
    private Long id;


    private Long userId;


    private Long roleId;


}