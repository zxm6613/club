package com.zxm.club.subject.domain.handler.subject;

import com.zxm.club.subject.common.enums.SubjectInfoTypeEnums;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectInfoDetailBO;

/**
 * 题目类型策略父接口
 */
public interface SubjectTypeStrategy {

    SubjectInfoTypeEnums getSubjectInfoTypeEnums();

    void add(SubjectInfoBO subjectInfoBO);

    SubjectInfoDetailBO select(SubjectInfoBO subjectInfoBO);

    void update(SubjectInfoBO subjectInfoBO);

    void delete(Integer id);
}
