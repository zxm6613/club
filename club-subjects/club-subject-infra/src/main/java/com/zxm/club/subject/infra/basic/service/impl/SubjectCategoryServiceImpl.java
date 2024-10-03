package com.zxm.club.subject.infra.basic.service.impl;

import com.zxm.club.subject.infra.basic.entity.SubjectCategory;
import com.zxm.club.subject.infra.basic.mapper.SubjectCategoryDao;
import com.zxm.club.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 刷题分类表(SubjectCategory)表服务实现类
 *
 * @author makejava
 * @since 2024-10-01 19:42:57
 */
@Service("subjectCategoryService")
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    @Resource
    private SubjectCategoryDao subjectCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectCategory queryById(Long id) {
        return this.subjectCategoryDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectCategory insert(SubjectCategory subjectCategory) {
        this.subjectCategoryDao.insert(subjectCategory);
        return subjectCategory;
    }

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectCategory subjectCategory) {
        return this.subjectCategoryDao.update(subjectCategory);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subjectCategoryDao.deleteById(id) > 0;
    }


    /**
     * 查询类别列表
     *
     * @return {@link List }<{@link SubjectCategory }>
     */
    @Override
    public List<SubjectCategory> queryCategoryList() {
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(0);
        return this.subjectCategoryDao.queryCategoryList(subjectCategory);
    }

    /**
     * 查询类别
     *
     * @param subjectCategory 主题类别
     * @return {@link List }<{@link SubjectCategory }>
     */
    @Override
    public List<SubjectCategory> queryCategory(SubjectCategory subjectCategory) {
        SubjectCategory category = new SubjectCategory();
        category.setParentId(subjectCategory.getId().intValue());
        return this.subjectCategoryDao.queryCategoryList(category);
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return subjectCategoryDao.deleteByIds(ids);
    }


}
