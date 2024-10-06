package com.zxm.club.oss.adapter;

import com.zxm.club.oss.entity.FileInfo;
import com.zxm.club.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

public class MinioStorageAdapter implements StorageAdapter {
    @Resource
    private MinioUtil minioUtil;

    @Override
    @SneakyThrows
    public void createBucket(String bucketName) {
        minioUtil.createBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) {
        minioUtil.createBucket(bucket);
        if (objectName != null) {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket,
                    objectName + "/" + uploadFile.getName());
        } else {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, uploadFile.getName());
        }
    }

    @Override
    @SneakyThrows
    public List<String> getAllBuckets() {
        return minioUtil.getAllBuckets();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getFiles(String bucket) {
        return minioUtil.getFiles(bucket);
    }

    @Override
    @SneakyThrows
    public InputStream download(String bucket, String objectName) {
        return minioUtil.download(bucket, objectName);
    }

    @Override
    @SneakyThrows
    public void deleteBucket(String bucket) {
        minioUtil.deleteBucket(bucket);
    }

    @Override
    @SneakyThrows
    public void deleteFile(String bucket, String objectName) {
        minioUtil.deleteFile(bucket, objectName);
    }
}
