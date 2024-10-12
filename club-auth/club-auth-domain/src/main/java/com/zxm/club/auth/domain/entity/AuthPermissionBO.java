package com.zxm.club.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限表(AuthPermission)实体类
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
@Data
public class AuthPermissionBO implements Serializable {
    private static final long serialVersionUID = 455147899073414594L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 父id 树形结构
     */
    private Long parentId;
    /**
     * 权限类型 0菜单 1操作
     */
    private Integer type;
    /**
     * 菜单路由
     */
    private String menuUrl;
    /**
     * 状态 0启用 1禁用
     */
    private Integer status;
    /**
     * 是否展示 0展示 1隐藏
     */
    private Integer show;
    /**
     * 图标
     */
    private String icon;
    /**
     * 权限唯一标识
     */
    private String permissionKey;
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
    /**
     * 是否删除
     */
    private Integer isDelete;

}

