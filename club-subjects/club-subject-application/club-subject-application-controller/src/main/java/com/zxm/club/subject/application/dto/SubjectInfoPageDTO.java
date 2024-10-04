package com.zxm.club.subject.application.dto;

import com.zxm.club.subject.common.entity.RequestPage;
import lombok.Data;

@Data
public class SubjectInfoPageDTO extends RequestPage {
    private Integer labelId;
    private Integer categoryId;
    private Integer difficult;
}
