package com.zxm.club.subject.application.convert;

import com.zxm.club.subject.application.dto.SubjectCategoryDto;
import com.zxm.club.subject.application.vo.OptionListVO;
import com.zxm.club.subject.application.vo.SubjectCategoryVO;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 主题类别 BoConverter
 *
 * @author zxm
 * @date 2024/10/02
 */
@Mapper
public interface SubjectOptionsBOConverter {
    SubjectOptionsBOConverter INSTANCE = Mappers.getMapper(SubjectOptionsBOConverter.class);
    //dto转BO
    List<OptionListVO> subjectOptionsBoToVo(List<OptionListBO> optionListBOS);

}
