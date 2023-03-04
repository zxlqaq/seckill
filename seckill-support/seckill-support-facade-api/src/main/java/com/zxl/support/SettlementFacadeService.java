package com.zxl.support;

import com.zxl.support.dto.Result;
import com.zxl.support.dto.SettlementDataDTO;
import com.zxl.support.dto.SettlementDataRequestDTO;
import com.zxl.support.dto.SettlementOrderDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface SettlementFacadeService {

    /**
     * 结算页初始化所需数据
     *    主要返回结算页所需的结算元素，包括用户地址，支付方式、配送时效、虚拟资产、价格相关数据（商品金额、抵扣金额等等）
     * @return
     */
    @PostMapping(value = "/seckill/support/settlement/settlementData", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<SettlementDataDTO> settlementData(SettlementDataRequestDTO requestDTO);

    /**
     * 下单
     * @param orderDTO
     * @return 订单号
     */
    @PostMapping(value = "/seckill/support/settlement/submitOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String> submitOrder(SettlementOrderDTO orderDTO);

    /**
     * 获取支付页URL
     */
    @GetMapping(value = "/seckill/support/settlement/getPayPageUrl", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String> getPayPageUrl(String orderId);

}
