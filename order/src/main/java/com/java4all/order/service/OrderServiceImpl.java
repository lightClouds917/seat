package com.java4all.order.service;

import com.java4all.order.dao.OrderDao;
import com.java4all.order.entity.Order;
import com.java4all.order.feign.AccountApi;
import com.java4all.order.feign.StorageApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author IT云清
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageApi storageApi;
    @Autowired
    private AccountApi accountApi;

    /**
     * 创建订单
     * @param order
     * @return
     */
    @Override
    public void create(Order order) {
        //本地方法
        orderDao.create(order);

        //远程方法
        storageApi.decrease(order.getProductId(),order.getCount());
        accountApi.decrease(order.getUserId(),order.getMoney());
    }
}
