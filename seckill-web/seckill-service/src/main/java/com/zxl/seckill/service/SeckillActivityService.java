package com.zxl.seckill.service;

import com.zxl.support.dto.Result;
import com.zxl.support.dto.SeckillActivityDTO;

public interface SeckillActivityService {

    Result<Integer> createActivity(SeckillActivityDTO activityDTO);

}
