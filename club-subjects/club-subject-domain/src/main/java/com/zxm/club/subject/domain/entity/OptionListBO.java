package com.zxm.club.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 选项列表
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Data
public class OptionListBO implements Serializable {
    private static final long serialVersionUID = 420568148031782050L;
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

