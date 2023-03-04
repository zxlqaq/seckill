package com.zxl.seckill.service.impl;

import com.zxl.seckill.feign.ActivityFacadeService;
import com.zxl.seckill.service.SeckillActivityService;
import com.zxl.support.dto.Result;
import com.zxl.support.dto.SeckillActivityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeckillActivityServiceImpl implements SeckillActivityService {
    private final ActivityFacadeService activityExportService;

    @Override
    public Result<Integer> createActivity(SeckillActivityDTO activityDTO) {
        return activityExportService.createActivity(activityDTO);
    }
}
