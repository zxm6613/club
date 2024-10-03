package com.zxm.club.subject.domain.handler.subject;

import com.zxm.club.subject.common.enums.SubjectInfoTypeEnums;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.infra.basic.entity.SubjectMultiple;
import com.zxm.club.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MultipleTypeStrategy implements SubjectTypeStrategy {
    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnums getSubjectInfoTypeEnums() {
        return SubjectInfoTypeEnums.MULTIPLE;
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
            SubjectMultiple subjectMultiple = new SubjectMultiple();
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setOptionType(optionListBO.getOptionType());
            subjectMultiple.setOptionContent(optionListBO.getOptionContent());
            subjectMultiple.setIsCorrect(optionListBO.getIsCorrect());
            subjectMultipleService.insert(subjectMultiple);
        });
    }
}
