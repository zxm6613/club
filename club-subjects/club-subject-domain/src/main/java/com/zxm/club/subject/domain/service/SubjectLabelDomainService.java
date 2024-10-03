package com.zxm.club.subject.domain.service;

import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import com.zxm.club.subject.domain.entity.SubjectLabelBO;

import java.util.List;

public interface SubjectLabelDomainService {

    /**
     * 新增标签
     *
     * @param subjectLabelBO 主题标签 bo
     */
    void add(SubjectLabelBO subjectLabelBO);


    /**
     * 查询标签列表
     *
     * @param categoryId 类别 ID
     * @return {@link List }<{@link SubjectLabelBO }>
     */
    List<SubjectLabelBO> queryLabelList(Long categoryId);

    /**
     * 更新标签
     *
     * @param subjectLabelBO 主题标签 bo
     * @return boolean
     */
    boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     *
     * @param ids IDS
     * @return boolean
     */
    boolean delete(List<Long> ids);
}
