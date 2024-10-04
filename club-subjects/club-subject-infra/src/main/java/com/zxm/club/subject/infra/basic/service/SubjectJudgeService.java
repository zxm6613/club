package com.zxm.club.subject.infra.basic.service;

import com.zxm.club.subject.infra.basic.entity.SubjectJudge;

import java.util.List;

/**
 * 判断题(SubjectJudge)表服务接口
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
public interface SubjectJudgeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectJudge queryById(Integer id);


    /**
     * 新增数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    SubjectJudge insert(SubjectJudge subjectJudge);

    /**
     * 修改数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    SubjectJudge update(SubjectJudge subjectJudge);

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
     * 通过subjectId查询判断题详情
     *
     * @param id 身份证
     * @return {@link SubjectJudge }
     */
    List<SubjectJudge> queryBySubjectId(Integer id);

    /**
     * 删除判断题
     *
     * @param id 身份证
     */
    void deleteBySubjectId(Integer id);
}
