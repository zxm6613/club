package com.zxm.club.subject.domain.convert;

import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectInfoPageBO;
import com.zxm.club.subject.infra.basic.entity.SubjectCategory;
import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.entity.SubjectInfoPage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoPageConverter {
    SubjectInfoPageConverter INSTANCE = Mappers.getMapper(SubjectInfoPageConverter.class);

    //对象类型转换，返回值是要转的对象，参数是用什么值转
    SubjectInfoPage subjectInfoPageBoToEntity(SubjectInfoPageBO subjectInfoPageBO);
    List<SubjectInfoBO> subjectInfoPageEntityToBO(List<SubjectInfo> subjectInfoList);
}