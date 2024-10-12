package com.zxm.club.auth.infra.basic.service;

import com.zxm.club.auth.infra.basic.entity.AuthPermissionRole;

import java.util.List;

/**
 * 权限角色关系表(AuthPermissionRole)表服务接口
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
public interface AuthPermissionRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthPermissionRole queryById(Long id);


    /**
     * 新增数据
     *
     * @param authPermissionRole 实例对象
     * @return 实例对象
     */
    AuthPermissionRole insert(AuthPermissionRole authPermissionRole);

    /**
     * 修改数据
     *
     * @param authPermissionRole 实例对象
     * @return 实例对象
     */
    AuthPermissionRole update(AuthPermissionRole authPermissionRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 插入批处理
     *
     * @param authPermissionRoleList auth 权限角色列表
     */
    void insertBatch(List<AuthPermissionRole> authPermissionRoleList);
}
