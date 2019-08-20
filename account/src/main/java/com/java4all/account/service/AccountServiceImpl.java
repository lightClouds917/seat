package com.java4all.account.service;

import com.java4all.account.dao.AccountDao;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author IT云清
 */
@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Override
    public void decrease(Integer userId, BigDecimal money) {
        accountDao.decrease(userId,money);
    }
}
