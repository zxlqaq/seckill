package com.zxl.support.facade.impl;

import com.zxl.support.SettlementFacadeService;
import com.zxl.support.SettlementService;
import com.zxl.support.entity.OrderRecord;
import com.zxl.support.dto.Result;
import com.zxl.support.dto.SettlementDataDTO;
import com.zxl.support.dto.SettlementDataRequestDTO;
import com.zxl.support.dto.SettlementOrderDTO;
import com.zxl.support.mapper.OrderRecordMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequiredArgsConstructor
public class SettlementExportServiceImpl implements SettlementFacadeService {

    Logger logger = LogManager.getLogger(SettlementExportServiceImpl.class);

    private final SettlementService settlementService;

    private final OrderRecordMapper orderRecordMapper;

    @Override
    public Result<SettlementDataDTO> settlementData(SettlementDataRequestDTO requestDTO) {
        SettlementDataDTO dataDTO = settlementService.settlementData(requestDTO);
        return new Result<>(dataDTO);
    }

    @Override
    public Result<String> submitOrder(SettlementOrderDTO orderDTO) {
        try{
            String orderId = settlementService.submitOrder(orderDTO);

            return new Result<>(orderId);
        }catch (Exception e){
            logger.error("提单异常",e);
        }
        return new Result<>(null);
    }

    @Override
    public Result<String> getPayPageUrl(String orderId) {
        OrderRecord orderRecord = orderRecordMapper.selectByOrderId(orderId);
        String payPageUrl = "http://localhost:8080/mock/payPage?orderId="+orderId+"&orderPrice="+orderRecord.getOrderPrice().toPlainString();
        return new Result<>(payPageUrl);
    }
}
