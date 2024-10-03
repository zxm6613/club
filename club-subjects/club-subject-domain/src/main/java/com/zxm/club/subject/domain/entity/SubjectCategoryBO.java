package com.zxm.club.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 刷题分类表(SubjectCategory)实体类
 *
 * @author makejava
 * @since 2024-10-01 19:42:54
 */
@Data
public class SubjectCategoryBO implements Serializable {
    private static final long serialVersionUID = 558653763669344341L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 图片链接
     */
    private String imageUrl;
    /**
     * 分类类型
     */
    private Integer categoryType;
    /**
     * 父级id
     */
    private Integer parentId;


}

