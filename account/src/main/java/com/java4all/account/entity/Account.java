package com.java4all.account.entity;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author IT云清
 */
@Data
public class Account {

    private Long id;

    /**用户id*/
    private Long userId;

    /**余额*/
    private BigDecimal balance;
}
