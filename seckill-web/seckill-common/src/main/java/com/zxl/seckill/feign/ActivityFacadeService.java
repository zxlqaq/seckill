package com.zxl.seckill.feign;

import com.zxl.support.dto.Result;
import com.zxl.support.dto.SeckillActivityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "${seckill.support.feign.name:seckill-support}",
        contextId = "support.ActivityExportService"
)
public interface ActivityFacadeService {

    /**
     * 查询活动库存
     * @param productId
     * @return
     */
    @GetMapping(value = "/seckill/support/activity/queryStore", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Integer> queryStore(@RequestParam("productId") String productId);

    /**
     * 创建活动
     * @param activityDTO
     * @return
     */
    @PostMapping(value = "/seckill/support/activity/createActivity", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Integer> createActivity(SeckillActivityDTO activityDTO);


    /**
     * 查询活动(查询活动最近的一场)
     * @param productId
     * @return
     */
    @GetMapping(value = "/seckill/support/activity/queryActivity", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<SeckillActivityDTO> queryActivity(@RequestParam("productId") String productId);

    /**
     * 查询活动
     * @param productId
     * @return
     */
    @GetMapping(value = "/seckill/support/activity/queryActivityByCondition", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<SeckillActivityDTO> queryActivityByCondition(@RequestParam("productId") String productId, @RequestParam("status") Integer status);

    /**
     * 活动开始
     * @param productId
     * @return
     */
    @PostMapping(value = "/seckill/support/activity/startActivity", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Integer> startActivity(String productId);

    /**
     * 活动关闭
     * @param productId
     * @return
     */
    @PostMapping(value = "/seckill/support/activity/endActivity", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Integer> endActivity(String productId);

}
