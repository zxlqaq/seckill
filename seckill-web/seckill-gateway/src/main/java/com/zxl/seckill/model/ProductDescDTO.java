package com.zxl.seckill.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductDescDTO implements Serializable {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private String tagStr;
}
