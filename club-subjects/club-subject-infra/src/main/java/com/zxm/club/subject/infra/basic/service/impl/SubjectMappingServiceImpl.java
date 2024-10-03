package com.zxm.club.subject.infra.basic.service.impl;

import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.entity.SubjectMapping;
import com.zxm.club.subject.infra.basic.mapper.SubjectMappingDao;
import com.zxm.club.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 题目分类标签映射(SubjectMapping)表服务实现类
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Service("subjectMappingService")
public class SubjectMappingServiceImpl implements SubjectMappingService {
    @Resource
    private SubjectMappingDao subjectMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectMapping queryById(Integer id) {
        return this.subjectMappingDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMapping insert(SubjectMapping subjectMapping) {
        this.subjectMappingDao.insert(subjectMapping);
        return subjectMapping;
    }

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMapping update(SubjectMapping subjectMapping) {
        this.subjectMappingDao.update(subjectMapping);
        return this.queryById(subjectMapping.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subjectMappingDao.deleteById(id) > 0;
    }

    /**
     * 插入批处理,题目，分类，标签关系
     *
     * @param id         身份证
     * @param categoryIds 类别 ID
     * @param labelIds    标签 ID
     */
    @Override
    public void insertBatch(Integer id, List<Integer> categoryIds, List<Integer> labelIds) {
        List<SubjectMapping> subjectMappingList = new ArrayList<>();
        for (Integer categoryId : categoryIds) {
            for (Integer labelId : labelIds) {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(id);
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setLabelId(labelId);
                subjectMappingList.add(subjectMapping);
            }
        }
        subjectMappingDao.insertBatch(subjectMappingList);
    }



}
