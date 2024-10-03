package com.zxm.club.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目分类标签映射(SubjectMapping)实体类
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Data
public class SubjectMappingBO implements Serializable {
    private static final long serialVersionUID = 799178164982603817L;
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
     * 分类id
     */
    private Integer categoryId;
    /**
     * 题目id
     */
    private Integer subjectId;
    /**
     * 标签id
     */
    private Integer labelId;

}

