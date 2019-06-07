package chenbo.work.crm.dao.settings.user.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

/**
 * @className PermissionVO
 * @authtor ChenBo
 * @date 2019/6/2
 */
@Data
public class PermissionVO {

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

    /** 是否有子节点 */
    private Integer hasChild;

    /** 子模块 */
    private ArrayList<PermissionVO> children;
}
