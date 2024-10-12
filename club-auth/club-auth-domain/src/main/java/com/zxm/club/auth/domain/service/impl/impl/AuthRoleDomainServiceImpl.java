package com.zxm.club.auth.domain.service.impl.impl;

import com.zxm.club.auth.domain.convert.AuthRoleConverter;
import com.zxm.club.auth.domain.entity.AuthPermissionRoleBO;
import com.zxm.club.auth.domain.entity.AuthRoleBO;
import com.zxm.club.auth.domain.service.impl.AuthRoleDomainService;
import com.zxm.club.auth.infra.basic.entity.AuthRole;
import com.zxm.club.auth.infra.basic.service.AuthRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    /**
     * 插入角色
     *
     * @param authRoleBO 身份验证角色 bo
     */
    @Override
    @Transactional
    public void insert(AuthRoleBO authRoleBO) {
        //插入之前查一下这个角色是否已经存在
        AuthRole role = this.authRoleService.queryByName(authRoleBO.getRoleName());
        if (role != null) throw new RuntimeException("这个角色已经存在");
        //类型转换
        AuthRole authRole = AuthRoleConverter.INSTANCE.convertToAuthRole(authRoleBO);
        try {
            this.authRoleService.insert(authRole);
        } catch (Exception e) {
            throw new RuntimeException("新增角色失败", e);
        }


    }

    /**
     * 更新角色
     *
     * @param authRoleBO 身份验证角色 bo
     */
    @Override
    public void update(AuthRoleBO authRoleBO) {
        //先查一下这个角色是否存在
        AuthRole role = authRoleService.queryById(authRoleBO.getId());
        if (role == null) throw new RuntimeException("这个角色不存在");
        //类型转换
        AuthRole authRole = AuthRoleConverter.INSTANCE.convertToAuthRole(authRoleBO);
        try {
            this.authRoleService.update(authRole);
        } catch (Exception e) {
            throw new RuntimeException("修改失败", e);
        }
        //TODO:修改角色的权限

    }

    /**
     * 删除
     *
     * @param id 身份证
     */
    @Override
    public void delete(Long id) {
        if (id == null) throw new RuntimeException("id不能为空");
        AuthRole authRole = this.authRoleService.queryById(id);
        if (authRole == null) throw new RuntimeException("这个角色不存在");
        try {
            this.authRoleService.logicDelete(id);
        } catch (Exception e) {
            throw new RuntimeException("删除失败", e);
        }
    }

    /**
     * 修改角色状态
     *
     * @param authRoleBO 身份验证角色 bo
     */
    @Override
    public void status(AuthRoleBO authRoleBO) {
        AuthRole authRole = this.authRoleService.queryById(authRoleBO.getId());
        if (authRole == null) throw new RuntimeException("这个角色不存在");
        try {
            authRole.setStatus(authRoleBO.getStatus()==0?1:0);
            authRoleService.update(authRole);
        } catch (Exception e) {
            throw new RuntimeException("修改角色状态失败",e);
        }
    }


}
