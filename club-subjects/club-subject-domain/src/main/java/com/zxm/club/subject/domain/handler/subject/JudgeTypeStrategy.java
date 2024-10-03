package com.zxm.club.subject.domain.handler.subject;

import com.zxm.club.subject.common.enums.SubjectInfoTypeEnums;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.infra.basic.entity.SubjectJudge;
import com.zxm.club.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class JudgeTypeStrategy implements SubjectTypeStrategy {
    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Override
    public SubjectInfoTypeEnums getSubjectInfoTypeEnums() {
        return SubjectInfoTypeEnums.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<OptionListBO> optionList = null;
        try {
            optionList = subjectInfoBO.getOptionListDTOS();
        } catch (Exception e) {
            throw new RuntimeException("选项列表不能为空！", e);
        }
        for (OptionListBO optionListBO : optionList) {
            SubjectJudge subjectJudge = new SubjectJudge();
            subjectJudge.setSubjectId(subjectInfoBO.getId());
            subjectJudge.setIsCorrect(optionListBO.getIsCorrect());
            subjectJudgeService.insert(subjectJudge);
        }
    }
}
