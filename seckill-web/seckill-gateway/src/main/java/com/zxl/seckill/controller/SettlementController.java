package com.zxl.seckill.controller;

import com.alibaba.fastjson.JSON;
import com.zxl.seckill.exception.BizException;
import com.zxl.seckill.limit.RateLimiterComponent;
import com.zxl.seckill.model.SettlementInitDTO;
import com.zxl.seckill.model.SettlementSubmitDTO;
import com.zxl.seckill.service.SettlementService;
import com.zxl.support.dto.SettlementOrderDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping( "/settlement" )
@RequiredArgsConstructor
public class SettlementController {

    private final SettlementService settlementService;

    private final RateLimiterComponent rateLimiterComponent;

    Logger logger = LogManager.getLogger(SettlementController.class);

    /**
     * 结算页初始化
     * @return
     */
    @RequestMapping(value = {"/initData"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public SettlementInitDTO initData(String productId, String buyNum, HttpServletRequest request) {

        logger.info("结算页初始化入参productId:"+productId+" ;buyNum="+buyNum);

        //判断是否被限流
        if(rateLimiterComponent.isLimitedByInit()){
            return null;
        }

        SettlementInitDTO initDTO = null;
        try {
            initDTO = settlementService.initData(productId, buyNum);
        } catch (BizException e) {
            return initDTO;
        }catch (Exception e){
            logger.error("初始化结算页接口异常",e);
        }
        return initDTO;
    }

    /**
     * 其他依赖数据的接口
     * @return
     */
    @RequestMapping(value = {"/dependency"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String dependency() {
        return "success!!!";
    }

    /**
     * 提交订单
     * @return
     */
    @RequestMapping(value = {"/submitData"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public SettlementSubmitDTO submitData(SettlementOrderDTO requestDTO){

        //判断是否被限流
        if(rateLimiterComponent.isLimitedBySubmit()){
            return null;
        }

        //mock数据
        requestDTO.setBuyNum(2);
        requestDTO.setAddress("jskjdkf");
        requestDTO.setPayType(1);


        SettlementSubmitDTO responseDTO = new SettlementSubmitDTO();
        responseDTO.setCode("000000");

        logger.info("结算页提单入参:"+ JSON.toJSONString(requestDTO));
        try {
            responseDTO = settlementService.submitOrder(requestDTO);
        } catch (BizException e) {
            responseDTO.setCode("100000");
            responseDTO.setCode(e.getMessage());
            return responseDTO;
        }catch (Exception e){
            responseDTO.setCode("100000");
            responseDTO.setMessage("系统出小差了，请稍后再试");
            logger.error("结算页提单入参",e);
        }

        return responseDTO;
    }

}
