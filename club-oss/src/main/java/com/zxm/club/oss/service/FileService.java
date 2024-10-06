package com.zxm.club.oss.service;

import com.zxm.club.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 文件服务抽取
 *
 * @author zxm
 * @date 2024/10/05
 */
@Service
public class FileService {

    private final StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    public List<String> getAllBuckets(){
        return storageAdapter.getAllBuckets();
    }
}
