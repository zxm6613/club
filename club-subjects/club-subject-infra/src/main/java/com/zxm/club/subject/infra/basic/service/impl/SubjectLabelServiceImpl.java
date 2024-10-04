package com.zxm.club.subject.infra.basic.service.impl;

import com.zxm.club.subject.infra.basic.entity.SubjectLabel;
import com.zxm.club.subject.infra.basic.mapper.SubjectLabelDao;
import com.zxm.club.subject.infra.basic.service.SubjectLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 刷题标签表(SubjectLabel)表服务实现类
 *
 * @author makejava
 * @since 2024-10-03 10:46:46
 */
@Service("subjectLabelService")
public class SubjectLabelServiceImpl implements SubjectLabelService {
    @Resource
    private SubjectLabelDao subjectLabelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectLabel queryById(Integer id) {
        return this.subjectLabelDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectLabel insert(SubjectLabel subjectLabel) {
        this.subjectLabelDao.insert(subjectLabel);
        return subjectLabel;
    }

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectLabel subjectLabel) {
        return this.subjectLabelDao.update(subjectLabel);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subjectLabelDao.deleteById(id) > 0;
    }

    /**
     * 按类别 ID 查询
     *
     * @param categoryId 类别 ID
     * @return {@link List }<{@link SubjectLabel }>
     */
    @Override
    public List<SubjectLabel> queryByCategoryId(Long categoryId) {
        return this.subjectLabelDao.queryByCategoryId(categoryId);
    }

    @Override
    public boolean delete(List<Long> ids) {
        if (ids.size() == 1){
            return this.deleteById(ids.get(0).intValue());
        }else {
            return this.subjectLabelDao.deleteByIds(ids);
        }
    }

    /**
     * 查询标签名称
     *
     * @param labelId 标签 ID
     * @return {@link String }
     */
    @Override
    public String queryLabelName(Integer labelId) {
        return this.subjectLabelDao.queryLabelName(labelId);
    }
}
