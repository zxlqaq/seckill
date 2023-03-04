package com.zxl.seckill.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SettlementSubmitDTO implements Serializable {

    private String code;
    private String message;
    private String payPageUrl;

    public SettlementSubmitDTO(String code, String message, String payPageUrl) {
        this.code = code;
        this.message = message;
        this.payPageUrl = payPageUrl;
    }

    public SettlementSubmitDTO() {
    }
}
