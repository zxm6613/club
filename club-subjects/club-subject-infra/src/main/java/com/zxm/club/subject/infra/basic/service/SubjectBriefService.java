package com.zxm.club.subject.infra.basic.service;

import com.zxm.club.subject.infra.basic.entity.SubjectBrief;

/**
 * 简答题(SubjectBrief)表服务接口
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
public interface SubjectBriefService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectBrief queryById(Integer id);


    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief insert(SubjectBrief subjectBrief);

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief update(SubjectBrief subjectBrief);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询标签名称
     *
     * @param labelId 标签 ID
     * @return {@link String }
     */
    String queryLabelName(Integer labelId);

    /**
     * 通过subjectId查询简答题详情
     *
     * @param id 身份证
     * @return {@link SubjectBrief }
     */
    SubjectBrief queryBySubjectId(Integer id);

    /**
     * 修改简答题
     *
     * @param subjectBrief 主题简介
     */
    void updateBySubjectId(SubjectBrief subjectBrief);

    /**
     * 删除简答题
     *
     * @param id 身份证
     */
    void deleteBySubjectId(Integer id);
}
