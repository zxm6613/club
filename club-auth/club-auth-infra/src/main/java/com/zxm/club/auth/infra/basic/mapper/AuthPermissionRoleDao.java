package com.zxm.club.auth.infra.basic.mapper;

import com.zxm.club.auth.infra.basic.entity.AuthPermissionRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 权限角色关系表(AuthPermissionRole)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
public interface AuthPermissionRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthPermissionRole queryById(Long id);

    /**
     * 统计总行数
     *
     * @param authPermissionRole 查询条件
     * @return 总行数
     */
    long count(AuthPermissionRole authPermissionRole);

    /**
     * 新增数据
     *
     * @param authPermissionRole 实例对象
     * @return 影响行数
     */
    int insert(AuthPermissionRole authPermissionRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthPermissionRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AuthPermissionRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthPermissionRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AuthPermissionRole> entities);

    /**
     * 修改数据
     *
     * @param authPermissionRole 实例对象
     * @return 影响行数
     */
    int update(AuthPermissionRole authPermissionRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 按角色 ID 查询
     *
     * @param id 身份证
     * @return {@link List }<{@link AuthPermissionRole }>
     */
    List<AuthPermissionRole> queryByRoleId(Long id);
}

