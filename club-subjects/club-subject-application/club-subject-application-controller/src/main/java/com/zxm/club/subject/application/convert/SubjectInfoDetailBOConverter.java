package com.zxm.club.subject.application.convert;

import com.zxm.club.subject.application.dto.SubjectInfoDTO;
import com.zxm.club.subject.application.vo.SubjectInfoDetailVO;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectInfoDetailBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoDetailBOConverter {
    SubjectInfoDetailBOConverter INSTANCE = Mappers.getMapper(SubjectInfoDetailBOConverter.class);
    SubjectInfoDetailVO subjectInfoDetailBoToVo(SubjectInfoDetailBO subjectInfoDetailBO);
}
