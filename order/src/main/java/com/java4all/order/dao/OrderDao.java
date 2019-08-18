package com.java4all.order.dao;

import com.java4all.order.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * @author IT云清
 */
@Repository
public interface OrderDao {

    /**
     * create order
     * @param order
     */
    void createOrder(Order order);
}
