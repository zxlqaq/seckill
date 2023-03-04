package com.zxl.seckill.service;


import com.zxl.seckill.exception.BizException;
import com.zxl.seckill.model.SettlementInitDTO;
import com.zxl.seckill.model.SettlementSubmitDTO;
import com.zxl.support.dto.SettlementOrderDTO;

public interface SettlementService {

    SettlementInitDTO initData(String productId, String buyNum) throws BizException;

    SettlementSubmitDTO submitOrder(SettlementOrderDTO requestDTO) throws BizException;

}
