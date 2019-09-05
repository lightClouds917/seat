package com.java4all.order.service;

import com.java4all.order.dao.OrderDao;
import com.java4all.order.entity.Order;
import com.java4all.order.feign.AccountApi;
import com.java4all.order.feign.StorageApi;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
     * 测试结果：
     * 1.添加本地事务：仅仅扣减库存
     * 2.不添加本地事务：创建订单，扣减库存
     */
    @Override
    @GlobalTransactional
    public void create(Order order) {
        //本地方法
        orderDao.create(order);
        //远程方法 扣减库存
        storageApi.decrease(order.getProductId(),order.getCount());

        //模拟远程调用出错，或者直接在account服务decrease方法中来模拟也可以
        System.out.println(5/0);
        //远程方法 扣减账户余额
        accountApi.decrease(order.getUserId(),order.getMoney());
    }
}
