package com.java4all.account.dao;

import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;

/**
 * @author IT云清
 */
public interface AccountDao {

    void decrease(@Param("userId") Integer userId, @Param("money") BigDecimal money);
}
