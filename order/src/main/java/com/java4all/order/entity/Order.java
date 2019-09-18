package com.java4all.order.entity;


import java.math.BigDecimal;
import lombok.Data;

/**
 * 订单
 * @author IT云清
 */
@Data
public class Order {

    private String id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

}
