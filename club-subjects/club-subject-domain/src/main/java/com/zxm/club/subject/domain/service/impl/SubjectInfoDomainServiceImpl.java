package com.zxm.club.subject.domain.service.impl;

import com.zxm.club.subject.common.entity.ResultPage;
import com.zxm.club.subject.domain.convert.SubjectInfoConverter;
import com.zxm.club.subject.domain.convert.SubjectInfoPageConverter;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectInfoDetailBO;
import com.zxm.club.subject.domain.entity.SubjectInfoPageBO;
import com.zxm.club.subject.domain.handler.subject.SubjectTypeFactory;
import com.zxm.club.subject.domain.handler.subject.SubjectTypeStrategy;
import com.zxm.club.subject.domain.service.SubjectInfoDomainService;
import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.entity.SubjectInfoPage;
import com.zxm.club.subject.infra.basic.service.SubjectInfoService;
import com.zxm.club.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目信息域服务实现
 *
 * @author zxm
 * @date 2024/10/03
 */
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectTypeFactory subjectTypeFactory;
    @Resource
    private SubjectMappingService subjectMappingService;

    /**
     * 新增题目
     *
     * @param subjectInfoBO 主题信息 bo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToLabel(subjectInfoBO);
        //插入题目详情
        subjectInfoService.insert(subjectInfo);
        //插入题型
        SubjectTypeStrategy subjectTypeStrategy = subjectTypeFactory
                .getSubjectTypeStrategy(subjectInfoBO.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        subjectTypeStrategy.add(subjectInfoBO);
        //插入题目分类标签关系表
        subjectMappingService.insertBatch(subjectInfoBO.getId(),
                subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId());
    }

    /**
     * 分页查询
     *
     * @param subjectInfoPageBO 主题信息页面 bo
     * @return {@link List }<{@link SubjectInfoBO }>
     */
    @Override
    public ResultPage<List<SubjectInfoBO>> pageQuery(SubjectInfoPageBO subjectInfoPageBO) {
        SubjectInfoPage subjectInfoPage = SubjectInfoPageConverter
                .INSTANCE.subjectInfoPageBoToEntity(subjectInfoPageBO);
        ResultPage<List<SubjectInfo>> subjectInfoList = subjectInfoService.pageQuery(subjectInfoPage);
        List<SubjectInfo> pageResultData = subjectInfoList.getT();
        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoConverter.INSTANCE.convertInfoToBo(pageResultData);
        ResultPage<List<SubjectInfoBO>> responsePageResult = new ResultPage<>();
        responsePageResult.setT(subjectInfoBOList);
        responsePageResult.setTotalPage(subjectInfoList.getTotalPage());
        responsePageResult.setTotal(subjectInfoList.getTotal());
        return responsePageResult;
    }

    /**
     * 查询题目详情
     *
     * @param subjectInfoBO 主题信息 bo
     * @return {@link SubjectInfoDetailBO }
     */
    @Override
    public SubjectInfoDetailBO subjectDetail(SubjectInfoBO subjectInfoBO) {
        //类型转换
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToLabel(subjectInfoBO);
        //先查一下题目类型，通过工厂策略模式对于的封装返回各自对应的数据
        SubjectInfo info = null;

        Integer subjectType = null;
        try {
            info = subjectInfoService.queryById(subjectInfo.getId());
            subjectType = info.getSubjectType();
        } catch (Exception e) {
            throw new RuntimeException("查不到id为" + subjectInfoBO.getId() + "的题");
        }
        SubjectTypeStrategy subjectTypeStrategy = subjectTypeFactory.getSubjectTypeStrategy(subjectType);
        return subjectTypeStrategy.select(subjectInfoBO);
    }

    /**
     * 更新题目
     *
     * @param subjectInfoBO 主题信息 bo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(SubjectInfoBO subjectInfoBO) {
        //类型转换，先修改一下主表
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToLabel(subjectInfoBO);
        SubjectInfo update = subjectInfoService.update(subjectInfo);
        if (update == null) {
            throw new RuntimeException("没有这个题目");
        }

        //工厂策略各自修改各自的值
        SubjectTypeStrategy subjectTypeStrategy = subjectTypeFactory.getSubjectTypeStrategy(subjectInfoBO.getSubjectType());
        subjectTypeStrategy.update(subjectInfoBO);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        //先查一下这个id对应的是哪个题型，不然下一步删了就查不到了
        SubjectInfo subjectInfo = subjectInfoService.queryById(id);
        //先删除主表
        try {
            subjectInfoService.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("删除失败，没有这个题",e);
        }
        //工厂加策略删除题型表
        SubjectTypeStrategy subjectTypeStrategy = subjectTypeFactory.getSubjectTypeStrategy(subjectInfo.getSubjectType());
        subjectTypeStrategy.delete(id);

        //删除关系表
        subjectMappingService.deleteBySubjectId(id);
    }

}
