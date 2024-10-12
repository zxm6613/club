package com.zxm.club.auth.infra.basic.service.impl;

import com.zxm.club.auth.infra.basic.entity.AuthPermissionRole;
import com.zxm.club.auth.infra.basic.mapper.AuthPermissionRoleDao;
import com.zxm.club.auth.infra.basic.service.AuthPermissionRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限角色关系表(AuthPermissionRole)表服务实现类
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
@Service("authPermissionRoleService")
public class AuthPermissionRoleServiceImpl implements AuthPermissionRoleService {
    @Resource
    private AuthPermissionRoleDao authPermissionRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthPermissionRole queryById(Long id) {
        return this.authPermissionRoleDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param authPermissionRole 实例对象
     * @return 实例对象
     */
    @Override
    public AuthPermissionRole insert(AuthPermissionRole authPermissionRole) {
        this.authPermissionRoleDao.insert(authPermissionRole);
        return authPermissionRole;
    }

    /**
     * 修改数据
     *
     * @param authPermissionRole 实例对象
     * @return 实例对象
     */
    @Override
    public AuthPermissionRole update(AuthPermissionRole authPermissionRole) {
        this.authPermissionRoleDao.update(authPermissionRole);
        return this.queryById(authPermissionRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authPermissionRoleDao.deleteById(id) > 0;
    }

    /**
     * 插入批处理
     *
     * @param authPermissionRoleList auth 权限角色列表
     */
    @Override
    public void insertBatch(List<AuthPermissionRole> authPermissionRoleList) {
        this.authPermissionRoleDao.insertBatch(authPermissionRoleList);
    }
}
