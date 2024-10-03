package com.zxm.club.subject.domain.handler.subject;

import com.zxm.club.subject.common.enums.SubjectInfoTypeEnums;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.infra.basic.entity.SubjectRedio;
import com.zxm.club.subject.infra.basic.service.SubjectRedioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedioTypeStrategy implements SubjectTypeStrategy {
    @Resource
    private SubjectRedioService subjectRedioService;

    @Override
    public SubjectInfoTypeEnums getSubjectInfoTypeEnums() {
        return SubjectInfoTypeEnums.REDIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<OptionListBO> optionListDTOS = null;
        try {
            optionListDTOS = subjectInfoBO.getOptionListDTOS();
        } catch (Exception e) {
            throw new RuntimeException("选项列表不能为空！", e);
        }
        optionListDTOS.forEach(optionListBO -> {
            SubjectRedio subjectRedio = new SubjectRedio();
            subjectRedio.setSubjectId(subjectInfoBO.getId());
            subjectRedio.setOptionType(optionListBO.getOptionType());
            subjectRedio.setOptionContent(optionListBO.getOptionContent());
            subjectRedio.setIsCorrect(optionListBO.getIsCorrect());
            subjectRedioService.insert(subjectRedio);
        });
    }
}
