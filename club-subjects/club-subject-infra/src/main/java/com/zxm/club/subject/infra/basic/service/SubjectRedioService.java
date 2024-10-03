package com.zxm.club.subject.infra.basic.service;

import com.zxm.club.subject.infra.basic.entity.SubjectRedio;

/**
 * 单选题(SubjectRedio)表服务接口
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
public interface SubjectRedioService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectRedio queryById(Integer id);

    /**
     * 新增数据
     *
     * @param subjectRedio 实例对象
     * @return 实例对象
     */
    SubjectRedio insert(SubjectRedio subjectRedio);

    /**
     * 修改数据
     *
     * @param subjectRedio 实例对象
     * @return 实例对象
     */
    SubjectRedio update(SubjectRedio subjectRedio);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
