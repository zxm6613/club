package com.zxm.club.subject.infra.basic.mapper;

import com.zxm.club.subject.infra.basic.entity.OptionList;
import com.zxm.club.subject.infra.basic.entity.SubjectMultiple;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 多选题(SubjectMultiple)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
public interface SubjectMultipleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMultiple queryById(Integer id);

    /**
     * 统计总行数
     *
     * @param subjectMultiple 查询条件
     * @return 总行数
     */
    long count(SubjectMultiple subjectMultiple);

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 影响行数
     */
    int insert(SubjectMultiple subjectMultiple);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectMultiple> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectMultiple> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectMultiple> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectMultiple> entities);

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 影响行数
     */
    int update(SubjectMultiple subjectMultiple);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询选项列表
     *
     * @param id 身份证
     * @return {@link List }<{@link OptionList }>
     */
    List<OptionList> queryBySubjectId(Integer id);

    /**
     * 根据subjectId查询选择题详情
     *
     * @param id 身份证
     * @return {@link SubjectMultiple }
     */
    SubjectMultiple querySubjectId(Integer id);

    /**
     * 查多选题实体列表
     *
     * @param id 身份证
     * @return {@link List }<{@link SubjectMultiple }>
     */
    List<SubjectMultiple> selectBySubjectId(Integer id);

    /**
     * 删除选择题
     *
     * @param id 身份证
     */
    void deleteBySubjectId(Integer id);
}

