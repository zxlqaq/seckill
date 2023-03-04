package com.zxl.support.impl;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.zxl.support.ActivityService;
import com.zxl.support.entity.ActivityInfo;
import com.zxl.support.entity.ProductInfo;
import com.zxl.support.exception.BizException;
import com.zxl.support.mapper.ActivityMapper;
import com.zxl.support.mapper.ProductInfoMapper;
import com.zxl.support.tools.RedisTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityMapper activityMapper;
    private final ProductInfoMapper productInfoMapper;
    private final RedisTools redisTools;

    @Override
    public int createActivity(ActivityInfo activityInfo) throws BizException {
        ActivityInfo existRecord = activityMapper.selectByCondition(activityInfo.getProductId(),null);
        if(existRecord!=null && (existRecord.getStatus()==1 || existRecord.getStatus()==0)){
            throw new BizException("活动已存在");
        }
        activityInfo.setStatus(0);
        return activityMapper.insert(activityInfo);
    }

    @Override
    public ActivityInfo queryActivityById(String productId) {
        return activityMapper.selectByProductId(productId);
    }

    @Override
    public ActivityInfo queryActivityByCondition(String productId,Integer status) {
        return activityMapper.selectByCondition(productId,status);
    }

    @Override
    public Integer startActivity(String productId) throws BizException {
        //查询未开始的
        ActivityInfo activityInfo = activityMapper.selectByCondition(productId,0);
        //判断时间是否在有效期内
        Date now = new Date();
        if(now.before(activityInfo.getActivityStart())){
            throw new BizException("活动尚未开始束");
        }
        if(now.after(activityInfo.getActivityEnd())){
            throw new BizException("活动已结束");
        }
        //更改活动为开始状态
        activityMapper.updateStatus(activityInfo.getId(),1);
        //更改商品标识为秒杀
        new LambdaUpdateChainWrapper<>(productInfoMapper)
                .set(ProductInfo::getTag, 2)
                .eq(ProductInfo::getProductId, productId)
                .update();

        //将库存放入Redis
        redisTools.set("store_"+productId,String.valueOf(activityInfo.getStockNum()));

        return 1;
    }

    @Override
    public Integer endActivity(String productId) throws BizException {
        //查询进行中的
        ActivityInfo activityInfo = activityMapper.selectByCondition(productId,1);
        //更改活动为结束状态
        activityMapper.updateStatus(activityInfo.getId(),2);
        //更改商品标识为普通
        new LambdaUpdateChainWrapper<>(productInfoMapper)
                .set(ProductInfo::getTag, 1)
                .eq(ProductInfo::getProductId, productId)
                .update();
        return 1;
    }

    @Override
    public Integer queryStore(String productId) {
        //查询进行中的
        ActivityInfo activityInfo = activityMapper.selectByCondition(productId,1);
        return activityInfo.getStockNum();
    }
}
