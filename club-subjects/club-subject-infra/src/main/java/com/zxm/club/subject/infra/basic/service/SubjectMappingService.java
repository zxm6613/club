package com.zxm.club.subject.infra.basic.service;

import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.entity.SubjectMapping;

import java.util.List;

/**
 * 题目分类标签映射(SubjectMapping)表服务接口
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
public interface SubjectMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMapping queryById(Integer id);


    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    SubjectMapping insert(SubjectMapping subjectMapping);

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    SubjectMapping update(SubjectMapping subjectMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    /**
     * 插入批处理
     *
     * @param id         身份证
     * @param categoryIds 类别 ID
     * @param labelIds    标签 ID
     */
    void insertBatch(Integer id, List<Integer> categoryIds, List<Integer> labelIds);


    /**
     * 通过题目id查询标签id
     *
     * @param id 身份证
     * @return {@link List }<{@link Integer }>
     */
    List<Integer> queryBySubjectId(Integer id);

    /**
     * 通过题目id删除关系表数据
     *
     * @param id 身份证
     */
    void deleteBySubjectId(Integer id);
}
