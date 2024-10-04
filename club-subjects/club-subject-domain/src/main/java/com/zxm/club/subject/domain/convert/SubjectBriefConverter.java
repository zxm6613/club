package com.zxm.club.subject.domain.convert;

import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.infra.basic.entity.SubjectBrief;
import com.zxm.club.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectBriefConverter {
    SubjectBriefConverter INSTANCE = Mappers.getMapper(SubjectBriefConverter.class);
    //对象类型转换，返回值是要转的对象，参数是用什么值转
    SubjectBrief subjectBriefBoToEntity(SubjectInfoBO subjectInfoBO);
}
