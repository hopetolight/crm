package chenbo.work.crm.dao.settings.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@TableName("tbl_permission")
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = -8601333764311070302L;

    @TableId
    private Long id;

    /** 父级主键 */
    private Long pid;

    /** 权限名称 */
    private String name;

    /** 权限编码 */
    private String code;

    /** 操作人 */
    private Long orderid;

    /** 操作时间 */
    private Date tempstamp;

    /** 状态 */
    private Integer status;

    /** 权限类型 */
    private Integer type;

}