package com.zxm.club.subject.infra.basic.service;


import com.zxm.club.subject.infra.basic.entity.SubjectLabel;

import java.util.List;

/**
 * 刷题标签表(SubjectLabel)表服务接口
 *
 * @author makejava
 * @since 2024-10-03 10:46:45
 */
public interface SubjectLabelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabel queryById(Integer id);


    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    SubjectLabel insert(SubjectLabel subjectLabel);

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int update(SubjectLabel subjectLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 按类别 ID 查询
     *
     * @param categoryId 类别 ID
     * @return {@link List }<{@link SubjectLabel }>
     */
    List<SubjectLabel> queryByCategoryId(Long categoryId);

    /**
     * 删除标签
     *
     * @param ids IDS
     * @return boolean
     */
    boolean delete(List<Long> ids);
}
