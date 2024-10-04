package com.zxm.club.subject.infra.basic.service;

import com.zxm.club.subject.common.entity.ResultPage;
import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.entity.SubjectInfoPage;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Integer id);


    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 分页查询
     *
     * @param subjectInfoPage 主题信息页面
     * @return {@link List }<{@link SubjectInfo }>
     */
    ResultPage<List<SubjectInfo>> pageQuery(SubjectInfoPage subjectInfoPage);
}
