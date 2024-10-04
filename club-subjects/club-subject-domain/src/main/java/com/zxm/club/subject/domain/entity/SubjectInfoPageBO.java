package com.zxm.club.subject.domain.entity;

import com.zxm.club.subject.common.entity.RequestPage;
import lombok.Data;

@Data
public class SubjectInfoPageBO extends RequestPage {
    private Integer labelId;
    private Integer categoryId;
    private Integer difficult;
}
