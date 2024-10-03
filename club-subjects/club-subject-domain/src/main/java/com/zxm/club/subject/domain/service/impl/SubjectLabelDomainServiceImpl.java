package com.zxm.club.subject.domain.service.impl;

import com.zxm.club.subject.domain.convert.SubjectLabelConverter;
import com.zxm.club.subject.domain.entity.SubjectLabelBO;
import com.zxm.club.subject.domain.service.SubjectLabelDomainService;
import com.zxm.club.subject.infra.basic.entity.SubjectLabel;
import com.zxm.club.subject.infra.basic.service.SubjectLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Autowired
    private SubjectLabelService subjectLabelService;

    /**
     * 新增标签
     *
     * @param subjectLabelBO 主题标签 bo
     */
    @Override
    public void add(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabelService.insert(subjectLabel);
    }

    /**
     * 查询标签列表
     *
     * @param categoryId 类别 ID
     * @return {@link List }<{@link SubjectLabelBO }>
     */
    @Override
    public List<SubjectLabelBO> queryLabelList(Long categoryId) {
        List<SubjectLabel> subjectLabels = subjectLabelService.queryByCategoryId(categoryId);
        return SubjectLabelConverter.INSTANCE.getSubjectLabelBOS(subjectLabels);
    }

    /**
     * 更新标签
     *
     * @param subjectLabelBO 主题标签 bo
     * @return boolean
     */
    @Override
    public boolean update(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    /**标签
     * 删除
     *
     * @param ids IDS
     * @return boolean
     */
    @Override
    public boolean delete(List<Long> ids) {
        return subjectLabelService.delete(ids);
    }
}
