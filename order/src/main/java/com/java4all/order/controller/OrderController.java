package com.java4all.order.controller;

import com.java4all.order.entity.Order;
import com.java4all.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author IT云清
 */
@RestController
@RequestMapping(value = "orderController")
public class OrderController {

    @Autowired
    private OrderService orderServiceImpl;

    @PostMapping("createOrder")
    public String createOrder(@RequestBody Order order){
        orderServiceImpl.createOrder(order);
        return "success";
    }
}
