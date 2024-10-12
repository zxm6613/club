package com.zxm.club.auth.infra.basic.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zxm.club.auth.infra.basic.entity.AuthUser;
import com.zxm.club.auth.infra.basic.mapper.AuthUserDao;
import com.zxm.club.auth.infra.basic.service.AuthUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息表(AuthUser)表服务实现类
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
@Service("authUserService")
public class AuthUserServiceImpl implements AuthUserService {
    @Resource
    private AuthUserDao authUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthUser queryById(Long id) {
        return this.authUserDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(AuthUser authUser) {
        if (authUser.getStatus() == null) authUser.setStatus(0);
        if (StrUtil.isBlank(authUser.getNickname()))
            authUser.setNickname(RandomUtil.randomString(10));
        authUser.setIsDelete(0);

        return this.authUserDao.insert(authUser) > 0;
    }

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AuthUser authUser) {
        return this.authUserDao.update(authUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authUserDao.deleteById(id) > 0;
    }

    /**
     * 按用户名查询
     *
     * @param username 用户名
     */
    @Override
    public int queryByUsername(String username) {
        return this.authUserDao.queryByUsername(username);
    }
}
