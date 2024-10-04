package com.zxm.club.subject.domain.handler.subject;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zxm.club.subject.common.enums.SubjectInfoTypeEnums;
import com.zxm.club.subject.domain.convert.SubjectInfoConverter;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectInfoDetailBO;
import com.zxm.club.subject.infra.basic.entity.SubjectBrief;
import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.entity.SubjectJudge;
import com.zxm.club.subject.infra.basic.service.SubjectInfoService;
import com.zxm.club.subject.infra.basic.service.SubjectJudgeService;
import com.zxm.club.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JudgeTypeStrategy implements SubjectTypeStrategy {
    @Resource
    private SubjectJudgeService subjectJudgeService;
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectMappingService subjectMappingService;

    @Override
    public SubjectInfoTypeEnums getSubjectInfoTypeEnums() {
        return SubjectInfoTypeEnums.JUDGE;
    }

    /**
     * 新增判断题
     *
     * @param subjectInfoBO 主题信息 bo
     */
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<OptionListBO> optionListDTOS = subjectInfoBO.getOptionListBOS();
        OptionListBO optionListBO = null;
        try {
            optionListBO = optionListDTOS.get(0);
        } catch (Exception e) {
            throw new RuntimeException("判断正误不能为空！", e);
        }
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(optionListBO.getIsCorrect());
        subjectJudgeService.insert(subjectJudge);
    }

    /**
     * 查询并封装判断题
     *
     * @param subjectInfoBO 主题信息 bo
     * @return {@link SubjectInfoDetailBO }
     */
    @Override
    public SubjectInfoDetailBO select(SubjectInfoBO subjectInfoBO) {
        //查询除过标签名称以外的属性，然后查到做对象拷贝
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToLabel(subjectInfoBO);
        List<SubjectJudge> subjectJudges = null;
        SubjectInfo info = null;
        try {
            subjectJudges = subjectJudgeService.queryBySubjectId(subjectInfo.getId());
            info = subjectInfoService.queryById(subjectInfo.getId());
        } catch (Exception e) {
            throw new RuntimeException("没有查到这个判断题");
        }
        SubjectInfoDetailBO subjectInfoDetailBO = BeanUtil.copyProperties(info, SubjectInfoDetailBO.class);

        //查询选项列表里面的正误
        List<OptionListBO> optionListBOList = new ArrayList<>();
        for (SubjectJudge subjectJudge : subjectJudges) {
            OptionListBO optionListBO = new OptionListBO();
            optionListBO.setIsCorrect(subjectJudge.getIsCorrect());
            optionListBOList.add(optionListBO);
        }
        subjectInfoDetailBO.setOptionListBOS(optionListBOList);

        //查询标签名称
        List<Integer> labelIds = subjectMappingService.queryBySubjectId(subjectInfoBO.getId());
        List<String> labelNames = new ArrayList<>();
        labelIds.forEach(labelId -> {
            String labelName = subjectJudgeService.queryLabelName(labelId);
            labelNames.add(labelName);
        });
        subjectInfoDetailBO.setLabelNames(labelNames);
        return subjectInfoDetailBO;
    }

    @Override
    public void update(SubjectInfoBO subjectInfoBO) {
        // 这里和选择题差不多，每一个subjectId对应多个选项，所以应该对每一个选项修改
        if (subjectInfoBO.getOptionListBOS().get(0).getIsCorrect() == null) return;
        //先查一下选项集合，一会便于用id去特定的修改每个选项
        List<SubjectJudge> subjectJudges = subjectJudgeService.queryBySubjectId(subjectInfoBO.getId());

        List<OptionListBO> optionListBOS = subjectInfoBO.getOptionListBOS();
        for (int i = 0; i < optionListBOS.size(); i++) {
            SubjectJudge subjectJudge = subjectJudges.get(i);
            subjectJudge.setIsCorrect(optionListBOS.get(i).getIsCorrect());
            subjectJudge.setSubjectId(subjectInfoBO.getId());
            subjectJudgeService.update(subjectJudge);
        }
    }

    /**
     * 删除判断题
     *
     * @param id 身份证
     */
    @Override
    public void delete(Integer id) {
        try {
            this.subjectJudgeService.deleteBySubjectId(id);
        } catch (Exception e) {
            throw new RuntimeException("删除判断题失败",e);
        }
    }


}
