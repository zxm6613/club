package com.zxm.club.subject.domain.handler.subject;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.zxm.club.subject.common.enums.SubjectInfoTypeEnums;
import com.zxm.club.subject.domain.convert.SubjectBriefConverter;
import com.zxm.club.subject.domain.convert.SubjectInfoConverter;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectInfoDetailBO;
import com.zxm.club.subject.infra.basic.entity.SubjectBrief;
import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.service.SubjectBriefService;
import com.zxm.club.subject.infra.basic.service.SubjectInfoService;
import com.zxm.club.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class BriefTypeStrategy implements SubjectTypeStrategy {

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectBriefService subjectBriefService;
    @Resource
    private SubjectMappingService subjectMappingService;


    @Override
    public SubjectInfoTypeEnums getSubjectInfoTypeEnums() {
        return SubjectInfoTypeEnums.BRIEF;
    }


    /**
     * 新增简答题
     *
     * @param subjectInfoBO 主题信息 bo
     */
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBrief.setSubjectAnswer(subjectInfoBO.getSubjectAnswer());
        subjectBriefService.insert(subjectBrief);
    }

    /**
     * 查询并封装简答题
     *
     * @param subjectInfoBO 主题信息 bo
     * @return {@link SubjectInfoDetailBO }
     */
    @Override
    public SubjectInfoDetailBO select(SubjectInfoBO subjectInfoBO) {
        //查询除过标签名称以外的属性，然后查到做对象拷贝
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToLabel(subjectInfoBO);
        SubjectBrief subjectBrief = null;
        SubjectInfo info = null;
        try {
            subjectBrief = subjectBriefService.queryBySubjectId(subjectInfo.getId());
            info = subjectInfoService.queryById(subjectInfo.getId());
        } catch (Exception e) {
            throw new RuntimeException("没有查到这个简答题");
        }
        SubjectInfoDetailBO subjectInfoDetailBO = BeanUtil.copyProperties(info, SubjectInfoDetailBO.class);
        subjectInfoDetailBO.setSubjectAnswer(subjectBrief.getSubjectAnswer());
        //查询标签名称
        List<Integer> labelIds = subjectMappingService.queryBySubjectId(subjectInfoBO.getId());
        List<String> labelNames = new ArrayList<>();
        labelIds.forEach(labelId -> {
            String labelName = subjectBriefService.queryLabelName(labelId);
            labelNames.add(labelName);
        });
        subjectInfoDetailBO.setLabelNames(labelNames);
        return subjectInfoDetailBO;
    }

    @Override
    public void update(SubjectInfoBO subjectInfoBO) {
        if (!StrUtil.isNotBlank(subjectInfoBO.getSubjectAnswer())) return;
        //类型转换
        SubjectBrief subjectBrief = SubjectBriefConverter.INSTANCE.subjectBriefBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBriefService.updateBySubjectId(subjectBrief);
    }

    /**
     * 删除简答题
     *
     * @param id 身份证
     */
    @Override
    public void delete(Integer id) {
        try {
            this.subjectBriefService.deleteBySubjectId(id);
        } catch (Exception e) {
            throw new RuntimeException("删除简答题失败", e);
        }
    }


}
