package com.zxm.club.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 多选题(SubjectMultiple)实体类
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Data
public class SubjectMultiple implements Serializable {
    private static final long serialVersionUID = -31073498211021671L;
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
     * 题目id
     */
    private Integer subjectId;
    /**
     * 选项类型
     */
    private Integer optionType;
    /**
     * 选项内容
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private Integer isCorrect;

}

