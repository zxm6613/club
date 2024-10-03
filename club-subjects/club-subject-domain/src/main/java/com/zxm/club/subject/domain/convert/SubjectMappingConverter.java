package com.zxm.club.subject.domain.convert;

import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectMappingBO;
import com.zxm.club.subject.infra.basic.entity.SubjectInfo;
import com.zxm.club.subject.infra.basic.entity.SubjectMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectMappingConverter {
    SubjectMappingConverter INSTANCE = Mappers.getMapper(SubjectMappingConverter.class);
    //对象类型转换，返回值是要转的对象，参数是用什么值转



}
