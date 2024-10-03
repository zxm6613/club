package com.zxm.club.subject.domain.convert;

import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import com.zxm.club.subject.domain.entity.SubjectLabelBO;
import com.zxm.club.subject.infra.basic.entity.SubjectCategory;
import com.zxm.club.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {
    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);
    //对象类型转换，返回值是要转的对象，参数是用什么值转
    SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> getSubjectLabelBOS(List<SubjectLabel> subjectLabels);
}
