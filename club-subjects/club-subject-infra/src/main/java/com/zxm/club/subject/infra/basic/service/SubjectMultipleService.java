package com.zxm.club.subject.infra.basic.service;

import com.zxm.club.subject.infra.basic.entity.OptionList;
import com.zxm.club.subject.infra.basic.entity.SubjectMultiple;

import java.util.List;

/**
 * 多选题(SubjectMultiple)表服务接口
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
public interface SubjectMultipleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMultiple queryById(Integer id);

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple insert(SubjectMultiple subjectMultiple);

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple update(SubjectMultiple subjectMultiple);

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
     * 查询选项列表
     *
     * @param id 身份证
     * @return {@link List }<{@link OptionList }>
     */
    List<OptionList> queryBySubjectId(Integer id);

    /**
     * 根据subjectId查询选择题详情
     *
     * @param id 身份证
     * @return {@link SubjectMultiple }
     */
    SubjectMultiple querySubjectId(Integer id);

    /**
     * 查多选题实体列表
     *
     * @param id 身份证
     * @return {@link List }<{@link SubjectMultiple }>
     */
    List<SubjectMultiple> selectBySubjectId(Integer id);

    /**
     * 删除选择题
     *
     * @param id 身份证
     */
    void deleteBySubjectId(Integer id);
}
