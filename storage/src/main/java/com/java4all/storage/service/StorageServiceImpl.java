package com.java4all.storage.service;

import com.java4all.storage.dao.StorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author IT云清
 */
@Service("storageServiceImpl")
public class StorageServiceImpl implements StorageService{

    @Autowired
    private StorageDao storageDao;

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId,count);
    }
}
