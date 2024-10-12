package com.zxm.club.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限角色关系表(AuthPermissionRole)实体类
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
@Data
public class AuthPermissionRoleDTO implements Serializable {
    private static final long serialVersionUID = -94686971550072080L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;
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

