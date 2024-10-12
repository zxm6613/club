package com.zxm.club.auth.infra.basic.service;

import com.zxm.club.auth.infra.basic.entity.AuthPermission;

/**
 * 权限表(AuthPermission)表服务接口
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
public interface AuthPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthPermission queryById(Long id);

    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    AuthPermission insert(AuthPermission authPermission);

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    AuthPermission update(AuthPermission authPermission);

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
     * @param name 名字
     * @return {@link AuthPermission }
     */
    AuthPermission queryByName(String name);
}
