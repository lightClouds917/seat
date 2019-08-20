package com.java4all.storage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhongxiang
 * @date 2019年08月20日 08:58:21
 */
@RestController
@RequestMapping("storage")
public class StorageController {

    @RequestMapping("test")
    public String test(String count){
        return "扣减了库存"+count;
    }

}
