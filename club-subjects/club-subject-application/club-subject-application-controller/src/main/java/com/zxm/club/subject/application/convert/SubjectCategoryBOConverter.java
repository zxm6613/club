package com.zxm.club.subject.application.convert;

import com.zxm.club.subject.application.vo.SubjectCategoryVO;
import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zxm.club.subject.application.dto.SubjectCategoryDto;

import java.util.List;

/**
 * 主题类别 BoConverter
 *
 * @author zxm
 * @date 2024/10/02
 */
@Mapper
public interface SubjectCategoryBOConverter {
    SubjectCategoryBOConverter INSTANCE = Mappers.getMapper(SubjectCategoryBOConverter.class);
    //dto转BO
    SubjectCategoryBO convertToSubjectCategoryBO(SubjectCategoryDto subjectCategory);
    //bo转vo
    List<SubjectCategoryVO> convertToSubjectCategoryVoList(List<SubjectCategoryBO> subjectCategoryBOS);

}
