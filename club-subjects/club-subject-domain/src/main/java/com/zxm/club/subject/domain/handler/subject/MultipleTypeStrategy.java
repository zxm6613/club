package com.zxm.club.subject.domain.handler.subject;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.zxm.club.subject.common.enums.SubjectInfoTypeEnums;
import com.zxm.club.subject.domain.convert.SubjectInfoConverter;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectInfoDetailBO;
import com.zxm.club.subject.infra.basic.entity.OptionList;
import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.entity.SubjectJudge;
import com.zxm.club.subject.infra.basic.entity.SubjectMultiple;
import com.zxm.club.subject.infra.basic.service.SubjectInfoService;
import com.zxm.club.subject.infra.basic.service.SubjectMappingService;
import com.zxm.club.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MultipleTypeStrategy implements SubjectTypeStrategy {
    @Resource
    private SubjectMultipleService subjectMultipleService;
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectMappingService subjectMappingService;

    @Override
    public SubjectInfoTypeEnums getSubjectInfoTypeEnums() {
        return SubjectInfoTypeEnums.MULTIPLE;
    }

    /**
     * 新增多选题
     *
     * @param subjectInfoBO 主题信息 bo
     */
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<OptionListBO> optionListDTOS = null;
        try {
            optionListDTOS = subjectInfoBO.getOptionListBOS();
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

    /**
     * 查询并封装多选题
     *
     * @param subjectInfoBO 主题信息 bo
     * @return {@link SubjectInfoDetailBO }
     */
    @Override
    public SubjectInfoDetailBO select(SubjectInfoBO subjectInfoBO) {
        //查询除过标签名称以外的属性，然后查到做对象拷贝
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToLabel(subjectInfoBO);
//        SubjectMultiple subjectMultiple = null;
        SubjectInfo info = null;
        try {
//            subjectMultiple = subjectMultipleService.querySubjectId(subjectInfo.getId());
            info = subjectInfoService.queryById(subjectInfo.getId());
        } catch (Exception e) {
            throw new RuntimeException("没有查到这个多选题");
        }
        SubjectInfoDetailBO subjectInfoDetailBO = BeanUtil.copyProperties(info, SubjectInfoDetailBO.class);
        //查询选项列表
        List<OptionList> optionLists = subjectMultipleService.queryBySubjectId(subjectInfoBO.getId());
        List<OptionListBO> optionListBOS = BeanUtil.copyToList(optionLists, OptionListBO.class);
        for (OptionListBO optionListBO : optionListBOS) {
            optionListBO.setOptionType(optionListBO.getOptionType());
            optionListBO.setIsCorrect(optionListBO.getIsCorrect());
            optionListBO.setOptionContent(optionListBO.getOptionContent());
        }
        subjectInfoDetailBO.setOptionListBOS(optionListBOS);

        //查询标签名称
        List<Integer> labelIds = subjectMappingService.queryBySubjectId(subjectInfoBO.getId());
        List<String> labelNames = new ArrayList<>();
        labelIds.forEach(labelId -> {
            String labelName = subjectMultipleService.queryLabelName(labelId);
            labelNames.add(labelName);
        });
        subjectInfoDetailBO.setLabelNames(labelNames);
        return subjectInfoDetailBO;
    }

    @Override
    public void update(SubjectInfoBO subjectInfoBO) {
        // 每一个subjectId对应多个选项，所以应该对每一个选项修改
        if (!CollUtil.isNotEmpty(subjectInfoBO.getOptionListBOS())) return;
        //先查一下选项集合，一会便于用id去特定的修改每个选项
        List<SubjectMultiple> subjectMultiples = subjectMultipleService.selectBySubjectId(subjectInfoBO.getId());

        List<OptionListBO> optionListBOS = subjectInfoBO.getOptionListBOS();
        for (int i = 0; i < optionListBOS.size(); i++) {
            SubjectMultiple subjectMultiple = subjectMultiples.get(i);
            subjectMultiple.setOptionType(optionListBOS.get(i).getOptionType());
            subjectMultiple.setIsCorrect(optionListBOS.get(i).getIsCorrect());
            subjectMultiple.setOptionContent(optionListBOS.get(i).getOptionContent());
            subjectMultipleService.update(subjectMultiple);
        }
    }

    /**
     * 删除选择题
     *
     * @param id 身份证
     */
    @Override
    public void delete(Integer id) {
        try {
            this.subjectMultipleService.deleteBySubjectId(id);
        } catch (Exception e) {
            throw new RuntimeException("删除选择题失败", e);
        }
    }
}
