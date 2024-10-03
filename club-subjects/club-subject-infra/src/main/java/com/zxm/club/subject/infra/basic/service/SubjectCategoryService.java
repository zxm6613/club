package com.zxm.club.subject.infra.basic.service;

import com.zxm.club.subject.infra.basic.entity.SubjectCategory;

import java.util.List;

/**
 * 刷题分类表(SubjectCategory)表服务接口
 *
 * @author makejava
 * @since 2024-10-01 19:42:56
 */
public interface SubjectCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectCategory queryById(Long id);



    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    SubjectCategory insert(SubjectCategory subjectCategory);

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    int update(SubjectCategory subjectCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询类别列表
     *
     * @return {@link List }<{@link SubjectCategory }>
     */
    List<SubjectCategory> queryCategoryList();

    /**
     * 查询小类别
     *
     * @param subjectCategory 主题类别
     * @return {@link List }<{@link SubjectCategory }>
     */
    List<SubjectCategory> queryCategory(SubjectCategory subjectCategory);

    /**
     * 按 ID 批量删除
     *
     * @param ids IDS
     * @return boolean
     */
    boolean deleteByIds(List<Long> ids);
}
