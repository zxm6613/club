package com.zxm.club.auth.domain.service.impl;


import com.zxm.club.auth.domain.entity.AuthPermissionBO;
import com.zxm.club.auth.domain.entity.AuthPermissionRoleBO;

import java.util.List;

/**
 * 角色权限域服务
 *
 * @author zxm
 * @date 2024/10/09
 */
public interface AuthRolePermissionDomainService {


    /**
     * 新增角色的权限
     *
     * @param authPermissionRoleBOS auth 权限角色 bos
     */
    void insert(List<AuthPermissionRoleBO> authPermissionRoleBOS);
}
