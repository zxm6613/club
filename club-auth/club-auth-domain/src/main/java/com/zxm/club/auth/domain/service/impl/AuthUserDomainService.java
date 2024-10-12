package com.zxm.club.auth.domain.service.impl;


import com.zxm.club.auth.domain.entity.AuthUserBO;

/**
 * auth 用户域服务
 *
 * @author zxm
 * @date 2024/10/09
 */
public interface AuthUserDomainService {
    /**
     * 插入用户
     *
     * @param authUserBO auth 用户 bo
     */
    void insert(AuthUserBO authUserBO) throws Exception;

    /**
     * 更新用户信息
     *
     * @param authUserBO auth 用户 bo
     */
    void update(AuthUserBO authUserBO);

    /**
     * 删除
     *
     * @param id 身份证
     */
    void delete(Long id);

    /**
     * 禁用和启用
     *
     * @param id 身份证
     */
    void updateStatus(Long id);

    /**
     * 查询用户信息
     *
     * @param id 身份证
     * @return {@link AuthUserBO }
     */
    AuthUserBO queryById(Long id);
}
