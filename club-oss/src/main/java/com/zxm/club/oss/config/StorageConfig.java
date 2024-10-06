package com.zxm.club.oss.config;

import com.zxm.club.oss.adapter.StorageAdapter;
import com.zxm.club.oss.adapter.AliOssStorageAdapter;
import com.zxm.club.oss.adapter.MinioStorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class StorageConfig {
    @Value("${oss.storyType}")
    private String storyType;

    @Bean
    @RefreshScope
    public StorageAdapter getStorageService() {
        if (storyType.equals("minio")) {
            return new MinioStorageAdapter();
        } else if (storyType.equals("aliyun")) {
            return new AliOssStorageAdapter();
        } else throw new IllegalArgumentException("没有这个存储服务");
    }

}
