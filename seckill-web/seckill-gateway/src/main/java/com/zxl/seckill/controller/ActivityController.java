package com.zxl.seckill.controller;

import com.zxl.seckill.cache.ILocalCache;
import com.zxl.seckill.feign.ActivityFacadeService;
import com.zxl.seckill.model.ActivityDetailDTO;
import com.zxl.support.dto.Result;
import com.zxl.support.dto.SeckillActivityDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping( "/activity" )
@RequiredArgsConstructor
public class ActivityController {

    Logger logger = LogManager.getLogger(ActivityController.class);

    private final ActivityFacadeService activityExportService;

    @Resource(name = "activityLocalCache")
    ILocalCache iLocalCache;

    /**
     * 查询活动库存
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = {"/queryStore"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Integer queryStore(String productId) {
        try{
            Result<Integer> result = activityExportService.queryStore(productId);
            return result.getData();
        }catch (Exception e){
            logger.error("query activity store exception:",e);
            return null;
        }
    }


    /**
     * 查询活动信息
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = {"/subQuery"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ActivityDetailDTO subQuery(String productId) {

        ActivityDetailDTO detailDTO = new ActivityDetailDTO();

        SeckillActivityDTO activityDTO = (SeckillActivityDTO)iLocalCache.get(productId);
        if(activityDTO == null){
            return null;
        }

        detailDTO.setProductPrice(activityDTO.getActivityPrice().toPlainString());
        detailDTO.setProductPictureUrl(activityDTO.getActivityPictureUrl());
        detailDTO.setProductName(activityDTO.getActivityName());

        Integer isAvailable = 1;
        if(activityDTO.getStockNum()<=0){
            isAvailable = 0;
        }
        Date now = new Date();
        if(now.before(activityDTO.getActivityStart()) || now.after(activityDTO.getActivityEnd())){
            isAvailable = 0;
        }
        detailDTO.setIsAvailable(isAvailable);

        return detailDTO;

    }

}
