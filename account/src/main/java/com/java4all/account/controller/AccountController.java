package com.java4all.account.controller;

import com.java4all.account.service.AccountService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author IT云清
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountServiceImpl;

    @RequestMapping("test")
    public String test(String count){
        return "扣减了"+count;
    }

    @RequestMapping("decrease")
    public String decrease(@RequestParam("userId") Integer userId,@RequestParam("money") BigDecimal money){
        accountServiceImpl.decrease(userId,money);
        return "Account decrease success";
    }
}
