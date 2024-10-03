package com.zxm.club.subject.domain.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zxm.club.subject.domain.convert.SubjectCategoryConverter;
import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import com.zxm.club.subject.domain.service.SubjectCategoryDomainService;
import com.zxm.club.subject.infra.basic.entity.SubjectCategory;
import com.zxm.club.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;


    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        /*领域层注重业务本身，和业务逻辑
        领域服务处理领域对象之间的业务操作，不处理技术细节。它解决与核心业务相关的问题。
        通常领域服务是无状态的，因为它仅仅封装业务逻辑，不存储数据。领域服务的输入和输出通常是领域对象。
        基础设施层的service职责主要是帮助领域层完成技术细节的实现，诸如数据库操作、消息传递、文件系统访问、网络调用等。
        */
        /*
         * 领域层（Domain Layer） 就像是 老板，负责决定业务的大方向，制定业务规则和逻辑。它关心的是业务应该如何运作，
         * 做哪些事情。老板不会亲自去执行技术细节，而是专注于决策和规划。
         * 基础设施层（Infrastructure Layer） 就像是 员工，负责具体的执行和实现。
         * 员工们会处理各种具体任务，比如与外部系统交互、存储数据、发送邮件等。基础设施层为领域层服务，帮助领域层实现其业务目标。
         * 这两者之间的关系很好地反映了分工合作：领域层负责定义业务逻辑，基础设施层负责提供技术支持，确保这些业务逻辑能够有效地运作。
         * */
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategoryService.insert(subjectCategory);
    }

    /**
     * 查询类别列表
     *
     * @return {@link List }<{@link SubjectCategoryBO }>
     */
    @Override
    public List<SubjectCategoryBO> queryCategoryList() {
        List<SubjectCategory> categoryList = subjectCategoryService.queryCategoryList();
        return SubjectCategoryConverter.INSTANCE.convertBoToSubjectCategory(categoryList);
    }

    /**
     * 查询小类别
     *
     * @param subjectCategoryBO 学科类别 bo
     * @return {@link List }<{@link SubjectCategoryBO }>
     */
    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        return SubjectCategoryConverter.INSTANCE.convertBoToSubjectCategory(subjectCategoryList);
    }

    /**
     * 更新分类
     *
     * @param subjectCategoryBO 学科类别 bo
     * @return boolean
     */
    @Override
    public boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    /**
     * 删除分类
     *
     * @param ids IDS
     * @return boolean
     */
    @Override
    public boolean delete(List<Long> ids) {
        boolean delete;
        if (ids.size() == 1) {
            //删除一个
            delete = subjectCategoryService.deleteById(ids.get(0).intValue());
        } else {
            //批量删除
            delete = subjectCategoryService.deleteByIds(ids);
        }
        return delete;
    }
}
