package com.zxm.club.auth.infra.basic.service;

import com.zxm.club.auth.infra.basic.entity.AuthRole;

/**
 * 角色信息表(AuthRole)表服务接口
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
public interface AuthRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRole queryById(Long id);

    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    AuthRole insert(AuthRole authRole);

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    AuthRole update(AuthRole authRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 按名称查询
     *
     * @param roleName 角色名称
     * @return {@link AuthRole }
     */
    AuthRole queryByName(String roleName);

    /**
     * 逻辑删除
     *
     * @param id 身份证
     */
    void logicDelete(Long id);
}
