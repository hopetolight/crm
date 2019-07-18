package chenbo.work.crm.dao.settings.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_role")
public class Role {

    @TableId
    private Long roleId;


    private String code;


    private String name;


    private String description;


}