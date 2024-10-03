package com.zxm.club.subject.domain.service;

import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import com.zxm.club.subject.infra.basic.entity.SubjectCategory;

import java.util.List;

public interface SubjectCategoryDomainService {
    /**
     * 新增分类
     *
     * @param subjectCategoryBO 学科类别 bo
     */
    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询类别列表
     *
     * @return {@link List }<{@link SubjectCategoryBO }>
     */
    List<SubjectCategoryBO> queryCategoryList();

    /**
     * 查询小类别
     *
     * @param subjectCategoryBO 学科类别 bo
     * @return {@link List }<{@link SubjectCategoryBO }>
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新分类
     *
     * @param subjectCategoryBO 学科类别 bo
     * @return boolean
     */
    boolean update(SubjectCategoryBO subjectCategoryBO);

    /**
     * 删除分类
     *
     * @param ids IDS
     * @return boolean
     */
    boolean delete(List<Long> ids);
}
