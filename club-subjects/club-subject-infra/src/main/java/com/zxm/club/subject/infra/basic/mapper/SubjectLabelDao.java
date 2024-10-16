package com.zxm.club.subject.infra.basic.mapper;


import com.zxm.club.subject.infra.basic.entity.SubjectLabel;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 刷题标签表(SubjectLabel)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-03 10:46:44
 */
public interface SubjectLabelDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabel queryById(Integer id);



    /**
     * 统计总行数
     *
     * @param subjectLabel 查询条件
     * @return 总行数
     */
    long count(SubjectLabel subjectLabel);

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 影响行数
     */
    int insert(SubjectLabel subjectLabel);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectLabel> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectLabel> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectLabel> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectLabel> entities);

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 影响行数
     */
    int update(SubjectLabel subjectLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 按类别 ID 查询
     *
     * @param categoryId 类别 ID
     * @return {@link List }<{@link SubjectLabel }>
     */
    List<SubjectLabel> queryByCategoryId(Long categoryId);

    /**
     * 按 ID 批量删除
     *
     * @param ids IDS
     * @return boolean
     */
    boolean deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 查询标签名称
     *
     * @param labelId 标签 ID
     * @return {@link String }
     */
    String queryLabelName(Integer labelId);
}

