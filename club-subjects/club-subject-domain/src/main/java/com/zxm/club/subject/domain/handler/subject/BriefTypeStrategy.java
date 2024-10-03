package com.zxm.club.subject.domain.handler.subject;

import com.zxm.club.subject.common.enums.SubjectInfoTypeEnums;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.infra.basic.entity.SubjectBrief;
import com.zxm.club.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BriefTypeStrategy implements SubjectTypeStrategy{

    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnums getSubjectInfoTypeEnums() {
        return SubjectInfoTypeEnums.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBrief.setSubjectAnswer(subjectInfoBO.getSubjectAnswer());
        subjectBriefService.insert(subjectBrief);
    }
}
