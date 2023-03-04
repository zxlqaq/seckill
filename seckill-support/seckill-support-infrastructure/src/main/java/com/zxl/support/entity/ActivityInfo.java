package com.zxl.support.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ActivityInfo {

    private long id;

    private String activityName;

    private String productId;

    private Date activityStart;

    private Date activityEnd;

    private Integer limitNum;

    private Integer stockNum;

    /**
     * 0:未开始  1：已开始  2：已结束
     */
    private Integer status;

    private String activityPictureUrl;

    private BigDecimal activityPrice;
}
