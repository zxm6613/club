package com.zxm.club.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 刷题标签表(SubjectLabel)实体类
 *
 * @author makejava
 * @since 2024-10-03 10:46:45
 */
@Data
public class SubjectLabelDTO implements Serializable {
    private static final long serialVersionUID = -78515944306429894L;
    /**
     * 主键
     */
    private Integer id;
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
     * 标签名称
     */
    private String labelName;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 标签排序
     */
    private Integer labelSort;

}

