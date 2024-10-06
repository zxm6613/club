package com.zxm.club.oss.adapter;

import com.zxm.club.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AliOssStorageAdapter implements StorageAdapter {
    @Override
    public void createBucket(String bucketName) {

    }

    @Override
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) {

    }

    @Override
    public List<String> getAllBuckets() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("aliuun");
        return arrayList;
    }

    @Override
    public List<FileInfo> getFiles(String bucket) {
        return null;
    }

    @Override
    public InputStream download(String bucket, String objectName) {
        return null;
    }

    @Override
    public void deleteBucket(String bucket) {

    }

    @Override
    public void deleteFile(String bucket, String objectName) {

    }
}
