package com.java4all.storage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author IT云清
 */
@Repository
public interface StorageDao {

    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
