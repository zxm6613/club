package com.zxm.club.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio 配置
 *
 * @author zxm
 * @date 2024/10/05
 */
@Configuration
public class MinioConfig {

    /**
     * minio地址
     */
    @Value("${minio.url}")
    private String url;
    /**
     * minio账号
     */
    @Value("${minio.accessKey}")
    private String accessKey;
    /**
     * minio密钥
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
    }
}
