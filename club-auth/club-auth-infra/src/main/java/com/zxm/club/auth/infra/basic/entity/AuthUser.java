package com.zxm.club.auth.infra.basic.entity;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户信息表(AuthUser)实体类
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
@Data
public class AuthUser implements Serializable {
    private static final long serialVersionUID = 659282159032335342L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 账号，唯一
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称,不传就给一个默认值
     */
    private String nickname = RandomUtil.randomString(10);
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 个人介绍
     */
    private String introduce = "这个人很懒，什么都没有写";
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态
     */
    private Integer status = 0;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 是否逻辑删除
     */
    private Integer isDelete = 0;
    /**
     * 特殊字段
     */
    private String extJson;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    private Date updatedTime;

}

