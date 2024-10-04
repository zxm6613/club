package com.zxm.club.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxm.club.subject.common.entity.ResultPage;
import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.entity.SubjectInfoPage;
import com.zxm.club.subject.infra.basic.mapper.SubjectInfoDao;
import com.zxm.club.subject.infra.basic.service.SubjectInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
@Service("subjectInfoService")
public class SubjectInfoServiceImpl implements SubjectInfoService {
    @Resource
    private SubjectInfoDao subjectInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectInfo queryById(Integer id) {
        return this.subjectInfoDao.selectById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectInfo insert(SubjectInfo subjectInfo) {
        this.subjectInfoDao.insert(subjectInfo);
        return subjectInfo;
    }

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectInfo update(SubjectInfo subjectInfo) {
        this.subjectInfoDao.update(subjectInfo);
        return this.queryById(subjectInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subjectInfoDao.deleteById(id) > 0;
    }

    /**
     * 分页查询
     *
     * @param subjectInfoPage 主题信息页面
     * @return {@link List }<{@link SubjectInfo }>
     */
    @Override
    public ResultPage<List<SubjectInfo>> pageQuery(SubjectInfoPage subjectInfoPage) {
        //分页
        Page<SubjectInfo> page = new Page<>(subjectInfoPage.getPageNo(), subjectInfoPage.getPageSize());

        IPage<SubjectInfo> subjectInfoIPage = subjectInfoDao.selectPage(page,
                        subjectInfoPage.getLabelId(),
                        subjectInfoPage.getCategoryId(),
                        subjectInfoPage.getDifficult()
                );
        List<SubjectInfo> subjectInfoList = subjectInfoIPage.getRecords();
        ResultPage<List<SubjectInfo>> resultPage = new ResultPage<>();
        resultPage.setTotalPage(subjectInfoIPage.getPages());
        resultPage.setTotal(subjectInfoIPage.getTotal());
        resultPage.setT(subjectInfoList);
        return resultPage;
    }
}
