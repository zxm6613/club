package com.zxm.club.oss.entity;

import lombok.Data;

@Data
public class FileInfo {
    private String etag;
    private String filetName;
    private long size;
}
