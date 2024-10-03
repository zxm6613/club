package com.zxm.club.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 题目信息表(SubjectInfo)实体类
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Data
public class SubjectInfo implements Serializable {
    private static final long serialVersionUID = 420568148031782050L;
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
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 出题人
     */
    private String settleUser;
    /**
     * 题目打分
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectPrase;
    /**
     * 题目类型
     */
    private Integer subjectType;

}

