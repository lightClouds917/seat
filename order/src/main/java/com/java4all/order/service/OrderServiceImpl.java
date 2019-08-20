package com.java4all.order.service;

import com.java4all.order.dao.OrderDao;
import com.java4all.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author IT云清
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;

    /**
     * 创建订单
     * @param order
     * @return
     */
    @Override
    public void create(Order order) {
        orderDao.create(order);
    }
}
