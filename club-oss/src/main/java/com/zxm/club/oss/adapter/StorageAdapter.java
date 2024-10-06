package com.zxm.club.oss.adapter;

import com.zxm.club.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 文件存储适配器
 *
 * @author zxm
 * @date 2024/10/05
 */
public interface StorageAdapter {
    /**
     * 创建 Bucket
     */
    void createBucket(String bucketName);

    /**
     * 上传文件
     */
    void uploadFile(MultipartFile uploadFile, String bucket, String objectName);

    /**
     * 获取全部存储桶
     */
    List<String> getAllBuckets();

    /**
     * 获取当前桶文件信息
     */
    List<FileInfo> getFiles(String bucket);

    /**
     * 下载文件
     */
    InputStream download(String bucket, String objectName);

    /**
     * 删除存储桶
     */
    public void deleteBucket(String bucket);

    /**
     * 删除文件
     */
    void deleteFile(String bucket, String objectName);
}
