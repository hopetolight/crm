package chenbo.work.crm.dao.settings.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tbl_user")
public class User {

    @TableId
    private Long userId;


    private Long deptId;


    private String userName;


    private String nickName;


    private String password;


    private String email;


    private Date expireTime;


    private String lockStatus;


    private String allowIps;


    private Long createBy;


    private Date createTime;


    private Long editBy;


    private Date editTime;


    private String realName;


}