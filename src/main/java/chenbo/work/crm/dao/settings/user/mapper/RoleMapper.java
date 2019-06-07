package chenbo.work.crm.dao.settings.user.mapper;

import chenbo.work.crm.dao.settings.user.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbg.generated
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbg.generated
     */
    Role selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbg.generated
     */
    List<Role> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Role record);
}