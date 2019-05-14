package chenbo.work.crm.mapper.user;

import chenbo.work.crm.entity.user.Permission;
import java.util.List;

public interface PermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_permission
     *
     * @mbg.generated
     */
    int insert(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_permission
     *
     * @mbg.generated
     */
    Permission selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_permission
     *
     * @mbg.generated
     */
    List<Permission> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Permission record);
}