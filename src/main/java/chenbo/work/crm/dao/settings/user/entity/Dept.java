package chenbo.work.crm.dao.settings.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_dept")
public class Dept {

    @TableId
    private Long deptId;


    private String no;


    private String name;


    private String manager;


    private String description;


    private String phone;


}