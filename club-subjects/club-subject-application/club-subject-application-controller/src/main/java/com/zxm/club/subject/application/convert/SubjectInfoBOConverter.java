package com.zxm.club.subject.application.convert;

import com.zxm.club.subject.application.dto.SubjectInfoDTO;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoBOConverter {
    SubjectInfoBOConverter INSTANCE = Mappers.getMapper(SubjectInfoBOConverter.class);
    SubjectInfoBO subjectInfoDtoToBo(SubjectInfoDTO subjectInfoDTO);
    List<OptionListBO> optionListDtoToBo(List<OptionListBO> optionListBO);
}
