package com.zxm.club.oss.util;

import com.zxm.club.oss.entity.FileInfo;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * minio文件操作工具
 *
 * @author zxm
 * @date 2024/10/05
 */
@Component
public class MinioUtil {
    @Resource
    private MinioClient minioClient;

    /**
     * 创建 Bucket
     */
    public void createBucket(String bucketName) throws Exception {
        boolean exist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 上传文件
     */
    public void uploadFile(InputStream inputStream, String bucket, String objectName) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucket).object(objectName)
                .stream(inputStream, -1, Integer.MAX_VALUE).build()); //-1表示不限制输入流的大小
    }

    /**
     * 获取全部存储桶
     */
    public List<String> getAllBuckets() throws Exception {
        List<Bucket> buckets = minioClient.listBuckets();
        return buckets.stream().map(Bucket::name).collect(Collectors.toList());
    }

    /**
     * 获取当前桶文件信息
     */
    public List<FileInfo> getFiles(String bucket) throws Exception {
        Iterable<Result<Item>> fileInfoList = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucket).build());
        List<FileInfo> fileInfos = new LinkedList<>();
        for (Result<Item> itemResult : fileInfoList) {
            Item item = itemResult.get();
            FileInfo fileInfo = new FileInfo();
            fileInfo.setEtag(item.etag());
            fileInfo.setSize(item.size());
            fileInfo.setFiletName(item.objectName());
            fileInfos.add(fileInfo);
        }
        return fileInfos;
    }

    /**
     * 下载文件
     */
    public InputStream download(String bucket, String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(objectName).build());
    }

    /**
     * 删除存储桶
     */
    public void deleteBucket(String bucket) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucket).build());
    }

    /**
     * 删除文件
     */
    public void deleteFile(String bucket, String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objectName).build());
    }

}
