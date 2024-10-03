package com.zxm.club.subject.domain.service;

import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;

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
}
