package com.zxm.club.auth.domain.service.impl.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zxm.club.auth.domain.entity.AuthPermissionRoleBO;
import com.zxm.club.auth.domain.service.impl.AuthRolePermissionDomainService;
import com.zxm.club.auth.infra.basic.entity.AuthPermission;
import com.zxm.club.auth.infra.basic.entity.AuthPermissionRole;
import com.zxm.club.auth.infra.basic.entity.AuthRole;
import com.zxm.club.auth.infra.basic.service.AuthPermissionRoleService;
import com.zxm.club.auth.infra.basic.service.AuthPermissionService;
import com.zxm.club.auth.infra.basic.service.AuthRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthPermissionRoleService authPermissionRoleService;
    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private AuthRoleService authRoleService;
    /**
     * 新增角色的权限
     *
     * @param authPermissionRoleBOS auth 权限角色 bos
     */
    @Override
    public void insert(List<AuthPermissionRoleBO> authPermissionRoleBOS) {
        List<AuthPermissionRole> authPermissionRoleList = new LinkedList<>();
        authPermissionRoleBOS.forEach(permissionRoleBO ->{
            AuthRole authRole = authRoleService.queryById(permissionRoleBO.getRoleId());
            AuthPermission authPermission = authPermissionService.queryById(permissionRoleBO.getPermissionId());
            if (authRole == null || authPermission == null) throw new RuntimeException("列表中的角色或者权限不存在");
            authPermissionRoleList.add(BeanUtil.copyProperties(permissionRoleBO,AuthPermissionRole.class));
        });
        try {
            authPermissionRoleService.insertBatch(authPermissionRoleList);
        } catch (Exception e) {
            throw new RuntimeException("角色的权限插入失败",e);
        }
    }
}
