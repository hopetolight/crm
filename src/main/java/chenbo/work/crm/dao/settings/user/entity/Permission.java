package chenbo.work.crm.dao.settings.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_permission")
public class Permission {

    @TableId
    private Long permissionId;


    private Long pid;


    private String name;


    private String moduleUrl;


    private String operUrl;


    private Long orderNo;


}