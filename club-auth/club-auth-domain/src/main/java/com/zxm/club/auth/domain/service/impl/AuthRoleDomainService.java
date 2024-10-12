package com.zxm.club.auth.domain.service.impl;


import com.zxm.club.auth.domain.entity.AuthPermissionRoleBO;
import com.zxm.club.auth.domain.entity.AuthRoleBO;
import com.zxm.club.auth.domain.entity.AuthUserBO;

import java.util.List;

/**
 * 角色域服务
 *
 * @author zxm
 * @date 2024/10/09
 */
public interface AuthRoleDomainService {

    /**
     * 插入角色
     *
     * @param authRoleBO 身份验证角色 bo
     */
    void insert(AuthRoleBO authRoleBO);

    /**
     * 更新角色
     *
     * @param authRoleBO 身份验证角色 bo
     */
    void update(AuthRoleBO authRoleBO);

    /**
     * 删除
     *
     * @param id 身份证
     */
    void delete(Long id);

    /**
     * 修改角色状态
     *
     * @param authRoleBO 身份验证角色 bo
     */
    void status(AuthRoleBO authRoleBO);


}
