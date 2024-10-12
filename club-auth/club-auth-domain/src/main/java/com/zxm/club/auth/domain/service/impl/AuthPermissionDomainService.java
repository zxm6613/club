package com.zxm.club.auth.domain.service.impl;


import com.zxm.club.auth.domain.entity.AuthPermissionBO;
import com.zxm.club.auth.domain.entity.AuthRoleBO;

/**
 * 权限域服务
 *
 * @author zxm
 * @date 2024/10/09
 */
public interface AuthPermissionDomainService {

    /**
     * 添加权限
     *
     * @param authPermissionBO auth 权限 bo
     */
    void insert(AuthPermissionBO authPermissionBO);

    /**
     * 删除权限
     *
     * @param id 身份证
     */
    void delete(Long id);

    /**
     * 更新权限
     *
     * @param authPermissionBO auth 权限 bo
     */
    void update(AuthPermissionBO authPermissionBO);

    /**
     * 修改状态是否启用
     *
     * @param authPermissionBO auth 权限 bo
     */
    void status(AuthPermissionBO authPermissionBO);

    /**
     * 权限是否展示
     *
     * @param authPermissionBO auth 权限 bo
     */
    void show(AuthPermissionBO authPermissionBO);
}
