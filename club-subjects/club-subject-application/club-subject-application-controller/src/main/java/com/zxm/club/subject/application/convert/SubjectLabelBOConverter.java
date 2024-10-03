package com.zxm.club.subject.application.convert;

import com.zxm.club.subject.application.dto.SubjectLabelDTO;
import com.zxm.club.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectLabelBOConverter {
    SubjectLabelBOConverter INSTANCE = Mappers.getMapper(SubjectLabelBOConverter.class);
    SubjectLabelBO getSubjectLabelBO(SubjectLabelDTO subjectLabelDTO);
}
