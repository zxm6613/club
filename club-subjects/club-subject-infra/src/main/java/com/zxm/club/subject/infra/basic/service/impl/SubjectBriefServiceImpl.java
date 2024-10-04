package com.zxm.club.subject.infra.basic.service.impl;

import com.zxm.club.subject.infra.basic.entity.SubjectBrief;
import com.zxm.club.subject.infra.basic.mapper.SubjectBriefDao;
import com.zxm.club.subject.infra.basic.service.SubjectBriefService;
import com.zxm.club.subject.infra.basic.service.SubjectLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 简答题(SubjectBrief)表服务实现类
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Service("subjectBriefService")
public class SubjectBriefServiceImpl implements SubjectBriefService {
    @Resource
    private SubjectBriefDao subjectBriefDao;
    @Resource
    private SubjectLabelService subjectLabelService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectBrief queryById(Integer id) {
        return this.subjectBriefDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectBrief insert(SubjectBrief subjectBrief) {
        this.subjectBriefDao.insert(subjectBrief);
        return subjectBrief;
    }

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectBrief update(SubjectBrief subjectBrief) {
        this.subjectBriefDao.update(subjectBrief);
        return this.queryById(subjectBrief.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subjectBriefDao.deleteById(id) > 0;
    }

    /**
     * 查询标签名称
     *
     * @param labelId 标签 ID
     * @return {@link String }
     */
    @Override
    public String queryLabelName(Integer labelId) {
        return subjectLabelService.queryLabelName(labelId);
    }

    /**
     * 通过subjectId查询简答题详情
     *
     * @param id 身份证
     * @return {@link SubjectBrief }
     */
    @Override
    public SubjectBrief queryBySubjectId(Integer id) {
        return this.subjectBriefDao.queryBySubjectId(id);
    }

    /**
     * 修改简答题
     *
     * @param subjectBrief 主题简介
     */
    @Override
    public void updateBySubjectId(SubjectBrief subjectBrief) {
        this.subjectBriefDao.updateBySubjectId(subjectBrief);
    }

    /**
     * 删除简答题
     *
     * @param id 身份证
     */
    @Override
    public void deleteBySubjectId(Integer id) {
        this.subjectBriefDao.deleteBySubjectId(id);
    }
}
