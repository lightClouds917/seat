package com.java4all.order.service;

import com.java4all.order.entity.Order;

/**
 * @author IT云清
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order
     * @return
     */
    void create(Order order);
}
