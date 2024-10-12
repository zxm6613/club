package com.zxm.club.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表(AuthRole)实体类
 *
 * @author makejava
 * @since 2024-10-06 20:15:31
 */
@Data
public class AuthRoleBO implements Serializable {
    private static final long serialVersionUID = -87499848279606283L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 状态 0启用 1禁用
     */
    private Integer status;
    /**
     * 角色唯一标识
     */
    private Long roleKey;
    
    private Integer dataRange;
    /**
     * 是否删除 0不删除 1删除
     */
    private Integer isDelete;
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

