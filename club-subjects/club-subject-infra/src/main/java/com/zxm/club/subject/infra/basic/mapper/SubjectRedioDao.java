package com.zxm.club.subject.infra.basic.mapper;

import com.zxm.club.subject.infra.basic.entity.SubjectRedio;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 单选题(SubjectRedio)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-03 15:08:56
 */
public interface SubjectRedioDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectRedio queryById(Integer id);


    /**
     * 统计总行数
     *
     * @param subjectRedio 查询条件
     * @return 总行数
     */
    long count(SubjectRedio subjectRedio);

    /**
     * 新增数据
     *
     * @param subjectRedio 实例对象
     * @return 影响行数
     */
    int insert(SubjectRedio subjectRedio);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectRedio> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectRedio> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectRedio> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectRedio> entities);

    /**
     * 修改数据
     *
     * @param subjectRedio 实例对象
     * @return 影响行数
     */
    int update(SubjectRedio subjectRedio);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

