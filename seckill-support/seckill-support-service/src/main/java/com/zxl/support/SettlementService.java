package com.zxl.support;

import com.zxl.support.dto.SettlementDataDTO;
import com.zxl.support.dto.SettlementDataRequestDTO;
import com.zxl.support.dto.SettlementOrderDTO;

public interface SettlementService {

    /**
     * 下单
     * @param orderDTO
     * @return 订单号
     */
    String submitOrder(SettlementOrderDTO orderDTO);


    SettlementDataDTO settlementData(SettlementDataRequestDTO requestDTO);
}
