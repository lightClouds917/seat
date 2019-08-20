package com.java4all.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhongxiang
 * @date 2019年08月20日 08:58:21
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @RequestMapping("test")
    public String test(String count){
        return "扣减了"+count;
    }

}
