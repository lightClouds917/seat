package com.java4all.account.service;

import java.math.BigDecimal;

/**
 * @author IT云清
 */
public interface AccountService {

    void decrease(Integer userId, BigDecimal money);
}
