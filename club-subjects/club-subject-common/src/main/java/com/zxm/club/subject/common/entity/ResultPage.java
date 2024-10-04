package com.zxm.club.subject.common.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResultPage<T> {
    private Long total = 0L;
    private Long totalPage = 0L;
    private T t = null;
}
