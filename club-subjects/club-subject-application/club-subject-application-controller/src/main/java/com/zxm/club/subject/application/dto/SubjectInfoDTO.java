package com.zxm.club.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)实体类
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Data
public class SubjectInfoDTO implements Serializable {
    private static final long serialVersionUID = 420568148031782050L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 题目名称
     */
    private String subjectName;
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
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 分类id
     */
    private List<Integer> categoryId;
    /**
     * 标签id
     */
    private List<Integer> labelId;
    /**
     * 选项列表
     */
    private List<OptionListDTO> optionListDTOS;

}

