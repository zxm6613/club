package com.zxm.club.subject.infra.basic.service.impl;

import com.zxm.club.subject.infra.basic.entity.SubjectRedio;
import com.zxm.club.subject.infra.basic.mapper.SubjectRedioDao;
import com.zxm.club.subject.infra.basic.service.SubjectRedioService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 单选题(SubjectRedio)表服务实现类
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Service("subjectRedioService")
public class SubjectRedioServiceImpl implements SubjectRedioService {
    @Resource
    private SubjectRedioDao subjectRedioDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectRedio queryById(Integer id) {
        return this.subjectRedioDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectRedio 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectRedio insert(SubjectRedio subjectRedio) {
        this.subjectRedioDao.insert(subjectRedio);
        return subjectRedio;
    }

    /**
     * 修改数据
     *
     * @param subjectRedio 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectRedio update(SubjectRedio subjectRedio) {
        this.subjectRedioDao.update(subjectRedio);
        return this.queryById(subjectRedio.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subjectRedioDao.deleteById(id) > 0;
    }
}
