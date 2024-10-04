package com.zxm.club.subject.infra.basic.service.impl;

import com.zxm.club.subject.infra.basic.entity.OptionList;
import com.zxm.club.subject.infra.basic.entity.SubjectMultiple;
import com.zxm.club.subject.infra.basic.mapper.SubjectMultipleDao;
import com.zxm.club.subject.infra.basic.service.SubjectLabelService;
import com.zxm.club.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 多选题(SubjectMultiple)表服务实现类
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Service("subjectMultipleService")
public class SubjectMultipleServiceImpl implements SubjectMultipleService {
    @Resource
    private SubjectMultipleDao subjectMultipleDao;
    @Resource
    private SubjectLabelService subjectLabelService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectMultiple queryById(Integer id) {
        return this.subjectMultipleDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMultiple insert(SubjectMultiple subjectMultiple) {
        this.subjectMultipleDao.insert(subjectMultiple);
        return subjectMultiple;
    }

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMultiple update(SubjectMultiple subjectMultiple) {
        this.subjectMultipleDao.update(subjectMultiple);
        return this.queryById(subjectMultiple.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subjectMultipleDao.deleteById(id) > 0;
    }

    /**
     * 查询标签名称
     *
     * @param labelId 标签 ID
     * @return {@link String }
     */
    @Override
    public String queryLabelName(Integer labelId) {
        return this.subjectLabelService.queryLabelName(labelId);
    }

    /**
     * 查询选项列表
     *
     * @param id 身份证
     * @return {@link List }<{@link OptionList }>
     */
    @Override
    public List<OptionList> queryBySubjectId(Integer id) {
        return this.subjectMultipleDao.queryBySubjectId(id);
    }

    /**
     * 根据subjectId查询选择题详情
     *
     * @param id 身份证
     * @return {@link SubjectMultiple }
     */
    @Override
    public SubjectMultiple querySubjectId(Integer id) {
        return this.subjectMultipleDao.querySubjectId(id);
    }

    /**
     * 查多选题实体列表
     *
     * @param id 身份证
     * @return {@link List }<{@link SubjectMultiple }>
     */
    @Override
    public List<SubjectMultiple> selectBySubjectId(Integer id) {
        return this.subjectMultipleDao.selectBySubjectId(id);
    }

    /**
     * 删除选择题
     *
     * @param id 身份证
     */
    @Override
    public void deleteBySubjectId(Integer id) {
        this.subjectMultipleDao.deleteBySubjectId(id);
    }
}
