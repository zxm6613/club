package com.zxm.club.subject.domain.service;

import com.zxm.club.subject.common.entity.ResultPage;
import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectInfoDetailBO;
import com.zxm.club.subject.domain.entity.SubjectInfoPageBO;

import java.util.List;

/**
 * 题目信息域服务
 *
 * @author zxm
 * @date 2024/10/03
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     *
     * @param subjectInfoBO 主题信息 bo
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询
     *
     * @param subjectInfoPageBO 主题信息页面 bo
     * @return {@link List }<{@link SubjectInfoBO }>
     */
    ResultPage<List<SubjectInfoBO>>  pageQuery(SubjectInfoPageBO subjectInfoPageBO);

    /**
     * 查询题目详情
     *
     * @param subjectInfoBO 主题信息 bo
     * @return {@link SubjectInfoDetailBO }
     */
    SubjectInfoDetailBO subjectDetail(SubjectInfoBO subjectInfoBO);

    /**
     * 更新题目
     *
     * @param subjectInfoBO 主题信息 bo
     */
    void update(SubjectInfoBO subjectInfoBO);

    /**
     * 删除
     *
     * @param id 身份证
     */
    void delete(Integer id);
}
