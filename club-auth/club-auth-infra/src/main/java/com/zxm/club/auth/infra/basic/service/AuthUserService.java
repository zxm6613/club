package com.zxm.club.auth.infra.basic.service;

import com.zxm.club.auth.infra.basic.entity.AuthUser;

/**
 * 用户信息表(AuthUser)表服务接口
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
public interface AuthUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUser queryById(Long id);

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    boolean insert(AuthUser authUser);

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    int update(AuthUser authUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 按用户名查询
     *
     * @param username 用户名
     */
    int queryByUsername(String username);
}
