package com.zxm.club.auth.domain.service.impl.impl;

import com.zxm.club.auth.domain.convert.AuthPermissionConverter;
import com.zxm.club.auth.domain.entity.AuthPermissionBO;
import com.zxm.club.auth.domain.service.impl.AuthPermissionDomainService;
import com.zxm.club.auth.infra.basic.entity.AuthPermission;
import com.zxm.club.auth.infra.basic.service.AuthPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthPermissionDomainImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;
    /**
     * 添加权限
     *
     * @param authPermissionBO auth 权限 bo
     */
    @Override
    public void insert(AuthPermissionBO authPermissionBO) {
        //先查一下这个权限是否存在
        AuthPermission permission = authPermissionService.queryByName(authPermissionBO.getName());
        if (permission != null) throw new RuntimeException("这个权限已存在");
        //类型转换
        AuthPermission authPermission = AuthPermissionConverter.INSTANCE.convertToAuthUser(authPermissionBO);
        try {
            authPermissionService.insert(authPermission);
        } catch (Exception e) {
            throw new RuntimeException("添加权限失败",e);
        }
    }

    /**
     * 删除权限
     *
     * @param id 身份证
     */
    @Override
    public void delete(Long id) {
        //先查一下这个权限是否存在
        AuthPermission permission = authPermissionService.queryById(id);
        if (permission == null) throw new RuntimeException("这个权限不存在");

        permission.setIsDelete(1);
        try {
            this.authPermissionService.update(permission);
        } catch (Exception e) {
            throw new RuntimeException("删除失败",e);
        }
    }

    /**
     * 更新权限
     *
     * @param authPermissionBO auth 权限 bo
     */
    @Override
    public void update(AuthPermissionBO authPermissionBO) {
        //先查一下这个权限是否存在
        AuthPermission permission = authPermissionService.queryById(authPermissionBO.getId());
        if (permission == null) throw new RuntimeException("这个权限不存在");

        //类型转换
        AuthPermission authPermission = AuthPermissionConverter.INSTANCE.convertToAuthUser(authPermissionBO);
        try {
            this.authPermissionService.update(authPermission);
        } catch (Exception e) {
            throw new RuntimeException("修改失败",e);
        }
    }

    /**
     * 修改状态是否启用
     *
     * @param authPermissionBO auth 权限 bo
     */
    @Override
    public void status(AuthPermissionBO authPermissionBO) {
        //先查一下这个权限是否存在
        AuthPermission permission = authPermissionService.queryById(authPermissionBO.getId());
        if (permission == null) throw new RuntimeException("这个权限不存在");

        permission.setStatus(permission.getStatus()==1?0:1);

        try {
            this.authPermissionService.update(permission);
        } catch (Exception e) {
            throw new RuntimeException("修改状态失败",e);
        }
    }

    /**
     * 权限是否展示
     *
     * @param authPermissionBO auth 权限 bo
     */
    @Override
    public void show(AuthPermissionBO authPermissionBO) {
        //先查一下这个权限是否存在
        AuthPermission permission = authPermissionService.queryById(authPermissionBO.getId());
        if (permission == null) throw new RuntimeException("这个权限不存在");

        permission.setShow(permission.getShow()==1?0:1);

        try {
            this.authPermissionService.update(permission);
        } catch (Exception e) {
            throw new RuntimeException("修改权限是否展示",e);
        }
    }
}
