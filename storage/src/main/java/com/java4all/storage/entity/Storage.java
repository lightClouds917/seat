package com.java4all.storage.entity;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author IT云清
 */
@Data
public class Storage {

    private Long id;

    /**产品id*/
    private Long productId;

    /**总库存*/
    private Integer total;

    /**已用库存*/
    private Integer used;

    /**剩余库存*/
    private Integer residue;
}
