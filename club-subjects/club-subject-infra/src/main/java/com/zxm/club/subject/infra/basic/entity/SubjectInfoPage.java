package com.zxm.club.subject.infra.basic.entity;

import com.zxm.club.subject.common.entity.RequestPage;
import lombok.Data;

@Data
public class SubjectInfoPage extends RequestPage {
    private Integer labelId;
    private Integer categoryId;
    private Integer difficult;
}
