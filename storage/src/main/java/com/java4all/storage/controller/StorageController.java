package com.java4all.storage.controller;

import com.java4all.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author IT云清
 */
@RestController
@RequestMapping("storage")
public class StorageController {

    @Autowired
    private StorageService storageServiceImpl;

    @RequestMapping("test")
    public String test(String count){
        return "扣减了库存"+count;
    }

    @GetMapping("decrease")
    public String decrease(Long productId,Integer count){
        storageServiceImpl.decrease(productId,count);
        return "Decrease storage success";
    }
}
