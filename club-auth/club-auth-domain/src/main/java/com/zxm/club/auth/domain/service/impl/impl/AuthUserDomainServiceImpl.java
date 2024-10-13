package com.zxm.club.auth.domain.service.impl.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import com.zxm.club.auth.domain.convert.AuthUserConverter;
import com.zxm.club.auth.domain.entity.AuthUserBO;
import com.zxm.club.auth.domain.redis.RedisUtil;
import com.zxm.club.auth.domain.service.impl.AuthUserDomainService;
import com.zxm.club.auth.domain.template.RoleTemplate;
import com.zxm.club.auth.infra.basic.entity.*;
import com.zxm.club.auth.infra.basic.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * auth 用户域服务 impl
 *
 * @author zxm
 * @date 2024/10/09
 */
@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    /*这里密码加密有三种方式：
    1.摘要加密，如md5，但这个不安全，如果我搞一个md5的库，破解数据库密码后，然后取库里找，可以找到原始密码，即使它不可逆，但没用，除非加一个盐
    2.对称加密，加密和解密都使用一个密钥,如sha1，sha256
    3.非对称加密，用公钥加密，私钥解密或者公钥解密，私钥加密，如rsa*/

    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private AuthRoleService authRoleService;
    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private AuthPermissionRoleService authPermissionRoleService;
    @Resource
    private RedisUtil redisUtil;

    private final String captchaPrefix = "code";

    private final String authPrefix = "auth";
    private final String rolePrefix = "role";

    private final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3eckxsIUacIjf2Pt/eanA8Hee1w9jM7S9e4KUaxbCgwqJkByXTYibdOezM1cAbWOcb5llpMXDJxIP2P2di9xTZ+OXQ+J89UCeTKVa/NT/a00nLlskFaa3ERg/ciLr00fB6aoT184iy/0tAI07kXm459QxpKCqU3YmnPzRsVWFvQIDAQAB";
    private final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALd5yTGwhRpwiN/Y+395qcDwd57XD2MztL17gpRrFsKDComQHJdNiJt057MzVwBtY5xvmWWkxcMnEg/Y/Z2L3FNn45dD4nz1QJ5MpVr81P9rTScuWyQVprcRGD9yIuvTR8HpqhPXziLL/S0AjTuRebjn1DGkoKpTdiac/NGxVYW9AgMBAAECgYADU23WPMDs7cVNaX2Fngr5uGIyuutIfRN1q9t+HULYW8/BFp+uDnW/e6sHkJKvK0x29X7uXMJI+1hUeTZ/uJ1RzdVas3qJ+glo64XFYzLzWsoAwFWoTjxiHiBpm/hZo20bEKm/Uvr/VwO+ogv1PYGY9RWOuC2lqLJOu+i2LvAscQJBAPEj3ki9GAiWAqFL13r/lrSA0ilXajErlcwaaoTjMINHqIVcRl2rXqwYKA6fc6jiMEDad+05kALqNFbTiADu3VMCQQDCyDoYL2yO2IQyGKvPVibUrIn0+vf54aBMNDESwK2Rx4kQhPhq+3pszkDlTZx+0npIew+Jo+fQ/B963B+HYZ6vAkAYmN0KtGcoMQ0RoOfY/wtDXESTvPvzCgwcprEo3vfK3FtfRxtSYLGmgkxLuY4VswTTCLXk99MtyPAPz0H3PmZLAkEAicoM1rkNqYtfEPVE7Ro7w+z+dq/nJfzHYcD2ChcFcf/eZTI7barScxAA9nVNxKVuXcG4Px0Uy1DfkBERuLqE/wJALI9W9Oj5+bJZsYO61jXYg7zAMsbAHmfM5SrlEF06X3UnPOPP/2FMoi93GWRvftLpVD55Mg9Ax7UfZz+kbRYBlw==";

    /**
     * 插入用户
     *
     * @param authUserBO auth 用户 bo
     */
    @Override
    @Transactional
    public void insert(AuthUserBO authUserBO) throws Exception {
        //先查一下这个用户名有没有
        if (authUserService.queryByUsername(authUserBO.getUsername()) == 1)
            throw new RuntimeException("用户已存在");

        //bo转entity
        AuthUser authUser = AuthUserConverter.INSTANCE.convertToAuthUser(authUserBO);
//        //加密
//        HashMap<String, String> reaMap = SaSecureUtil.rsaGenerateKeyPair();
//        String publicKey = reaMap.get("public");
//        System.out.println("publicKey = " + publicKey);
//        String privateKey = reaMap.get("private");
//        System.out.println("privateKey = " + privateKey);
        if (authUser.getPassword() != null){
            //密码加密，使用rsa非对称加密
            authUser.setPassword(SaSecureUtil.rsaEncryptByPublic(publicKey, authUserBO.getPassword()));
            System.out.println("密码解密后为：" + SaSecureUtil.rsaDecryptByPrivate(privateKey, authUser.getPassword()));
        }
        boolean success = authUserService.insert(authUser);
        if (!success) throw new RuntimeException("注册失败");
        //TODO:分配用户的角色，默认给每一个用户分配一个初级角色basic,这里使用缓存来把本次用户的角色和角色有的权限放进缓存
        AuthRole authRole = authRoleService.queryByName(RoleTemplate.BASIC_USER);
        if (authRole == null) throw new RuntimeException("basic角色不存在");
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(authUser.getId());
        authUserRole.setRoleId(authRole.getId());
        try {
            authUserRoleService.insert(authUserRole);
        } catch (Exception e) {
            throw new RuntimeException("给用户分配角色失败", e);
        }

        //角色缓存
        List<AuthRole> authRoleList = new LinkedList<>();
        String userRole = redisUtil.buildKey(rolePrefix, authUser.getUsername());
        authRoleList.add(authRole);
        redisUtil.set(userRole, new Gson().toJson(authRoleList));

        //权限缓存
        List<AuthPermission> authPermissionList = new LinkedList<>();
        List<AuthPermissionRole> authPermissionRoleList = authPermissionRoleService.queryByRoleId(authRole.getId());
        authPermissionRoleList.forEach(authPermissionRole -> {
            AuthPermission authPermission = authPermissionService.queryById(authPermissionRole.getPermissionId());
            authPermissionList.add(authPermission);
        });
        List<String> permissionNameList = authPermissionList.stream().map(AuthPermission::getName).collect(Collectors.toList());
        String authKey = redisUtil.buildKey(authPrefix, authUser.getUsername());
        redisUtil.set(authKey, new Gson().toJson(permissionNameList));

    }

    /**
     * 更新
     *
     * @param authUserBO auth 用户 bo
     */
    @Override
    public void update(AuthUserBO authUserBO) {
        //1.修改密码 2.修改用户名
        AuthUser user = authUserService.queryById(authUserBO.getId());
        if (user == null) throw new RuntimeException("没有这个用户");
        int count = authUserService.queryByUsername(authUserBO.getUsername());
        if (count > 0) throw new RuntimeException("这个用户名已经存在，请换个用户名");
        AuthUser authUser = AuthUserConverter.INSTANCE.convertToAuthUser(authUserBO);
        authUser.setPassword(SaSecureUtil.rsaEncryptByPublic(publicKey, authUser.getPassword()));
        try {
            authUserService.update(authUser);
        } catch (Exception e) {
            throw new RuntimeException("修改失败", e);
        }
    }

    /**
     * 删除
     *
     * @param id 身份证
     */
    @Override
    public void delete(Long id) {
        AuthUser authUser = authUserService.queryById(id);
        if (authUser == null) throw new RuntimeException("用户不存在");
        authUser.setIsDelete(1);
        authUserService.update(authUser);
    }

    /**
     * 禁用和启用
     *
     * @param id 身份证
     */
    @Override
    public void updateStatus(Long id) {
        AuthUser authUser = authUserService.queryById(id);
        if (authUser == null) throw new RuntimeException("用户不存在");
        authUser.setStatus(authUser.getStatus() == 1 ? 0 : 1);
        authUserService.update(authUser);
    }

    /**
     * 查询用户信息
     *
     * @param id 身份证
     * @return {@link AuthUserBO }
     */
    @Override
    public AuthUserBO queryById(Long id) {
        AuthUser authUser = authUserService.queryById(id);
        if (authUser == null) throw new RuntimeException("用户不存在");
        //密码解密
        authUser.setPassword(SaSecureUtil.rsaDecryptByPrivate(privateKey, authUser.getPassword()));

        return AuthUserConverter.INSTANCE.convertToAuthUserBO(authUser);
    }

    /**
     * do login
     *
     * @param code 法典
     */
    @Override
    @Transactional
    public void doLogin(String code) {
        String codeKey = redisUtil.buildKey(captchaPrefix, code);
        String openId = redisUtil.get(codeKey);
        if (openId == null) throw new RuntimeException("验证码错误");

        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUsername(openId);
        try {
            this.insert(authUserBO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        StpUtil.login(openId);
    }
}
