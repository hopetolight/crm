package chenbo.work.crm.dao.settings.user.model;

import chenbo.work.crm.dao.settings.user.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @className PermissionVO
 * @authtor ChenBo
 * @date 2019/7/18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVO extends Permission implements Serializable {
    private static final long serialVersionUID = 278976684358278128L;

    private List<PermissionVO> children;
}
