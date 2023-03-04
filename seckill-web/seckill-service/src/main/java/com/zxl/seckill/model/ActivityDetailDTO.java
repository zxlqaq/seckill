package com.zxl.seckill.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityDetailDTO implements Serializable {

    private String productName;

    private String productPrice;

    private String productPictureUrl;

    private Integer isAvailable;
}
