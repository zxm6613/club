package com.zxm.club.subject.domain.handler.subject;

import com.zxm.club.subject.common.enums.SubjectInfoTypeEnums;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SubjectTypeFactory {
    @Resource
    private List<SubjectTypeStrategy> subjectTypeStrategyList;

    private final Map<SubjectInfoTypeEnums, SubjectTypeStrategy> map = new HashMap<>(4);


    /**
     * 初始化map
     */
    @PostConstruct
    private void initialize() {
        subjectTypeStrategyList.forEach(subjectTypeStrategy ->
                map.put(subjectTypeStrategy.getSubjectInfoTypeEnums(), subjectTypeStrategy));
    }

    public SubjectTypeStrategy getSubjectTypeStrategy(Integer subjectType) {
        try {
            SubjectInfoTypeEnums subjectInfoTypeEnums = SubjectInfoTypeEnums.getByCode(subjectType);
            return map.get(subjectInfoTypeEnums);
        } catch (Exception e) {
            throw new RuntimeException("没有这种题型");
        }
    }
}
