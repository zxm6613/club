package com.zxm.club.subject.application.convert;

import com.zxm.club.subject.application.dto.SubjectInfoDTO;
import com.zxm.club.subject.application.dto.SubjectInfoPageDTO;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectInfoPageBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoPageBOConverter {
    SubjectInfoPageBOConverter INSTANCE = Mappers.getMapper(SubjectInfoPageBOConverter.class);
    SubjectInfoPageBO subjectInfoPageBOToDTO(SubjectInfoPageDTO subjectInfoPageDTO);
}
