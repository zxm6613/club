package com.zxm.club.subject.infra.basic.service.impl;

import com.zxm.club.subject.infra.basic.entity.SubjectJudge;
import com.zxm.club.subject.infra.basic.mapper.SubjectJudgeDao;
import com.zxm.club.subject.infra.basic.service.SubjectJudgeService;
import com.zxm.club.subject.infra.basic.service.SubjectLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 判断题(SubjectJudge)表服务实现类
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Service("subjectJudgeService")
public class SubjectJudgeServiceImpl implements SubjectJudgeService {
    @Resource
    private SubjectJudgeDao subjectJudgeDao;
    @Resource
    private SubjectLabelService subjectLabelService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectJudge queryById(Integer id) {
        return this.subjectJudgeDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectJudge insert(SubjectJudge subjectJudge) {
        this.subjectJudgeDao.insert(subjectJudge);
        return subjectJudge;
    }

    /**
     * 修改数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectJudge update(SubjectJudge subjectJudge) {
        this.subjectJudgeDao.update(subjectJudge);
        return this.queryById(subjectJudge.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subjectJudgeDao.deleteById(id) > 0;
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
     * 通过subjectId查询判断题详情
     *
     * @param id 身份证
     * @return {@link SubjectJudge }
     */
    @Override
    public List<SubjectJudge> queryBySubjectId(Integer id) {
        return this.subjectJudgeDao.queryBySubjectId(id);
    }

    /**
     * 删除判断题
     *
     * @param id 身份证
     */
    @Override
    public void deleteBySubjectId(Integer id) {
        this.subjectJudgeDao.deleteBySubjectId(id);
    }
}
