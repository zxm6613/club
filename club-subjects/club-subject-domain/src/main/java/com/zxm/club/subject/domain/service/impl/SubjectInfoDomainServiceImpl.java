package com.zxm.club.subject.domain.service.impl;

import com.zxm.club.subject.domain.convert.SubjectInfoConverter;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.handler.subject.SubjectTypeFactory;
import com.zxm.club.subject.domain.handler.subject.SubjectTypeStrategy;
import com.zxm.club.subject.domain.service.SubjectInfoDomainService;
import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.service.SubjectInfoService;
import com.zxm.club.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 题目信息域服务实现
 *
 * @author zxm
 * @date 2024/10/03
 */
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectTypeFactory subjectTypeFactory;
    @Resource
    private SubjectMappingService subjectMappingService;

    /**
     * 新增题目
     *
     * @param subjectInfoBO 主题信息 bo
     */
    @Transactional
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToLabel(subjectInfoBO);
        //插入题目详情
        subjectInfoService.insert(subjectInfo);
        //插入题型
        SubjectTypeStrategy subjectTypeStrategy = subjectTypeFactory
                .getSubjectTypeStrategy(subjectInfoBO.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        subjectTypeStrategy.add(subjectInfoBO);
        //插入题目分类标签关系表
        subjectMappingService.insertBatch(subjectInfoBO.getId(),
                subjectInfoBO.getCategoryId(),subjectInfoBO.getLabelId());
    }
}
