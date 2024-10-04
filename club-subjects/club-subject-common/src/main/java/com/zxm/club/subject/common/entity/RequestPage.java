package com.zxm.club.subject.common.entity;

import lombok.Data;

@Data
public class RequestPage {
    private Integer pageSize = 5;
    private Integer pageNo = 1;
}
