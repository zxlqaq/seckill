package com.zxl.seckill.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ActivityDescDTO implements Serializable {
    private String activityName;

    private String productId;

    private String activityStartStr;

    private String activityEndStr;

    private Integer limitNum;

    private Integer stockNum;

    private BigDecimal activityPrice;

    private String statusStr;
}
