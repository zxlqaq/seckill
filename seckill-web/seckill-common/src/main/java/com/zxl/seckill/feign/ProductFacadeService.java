package com.zxl.seckill.feign;

import com.zxl.support.dto.ProductInfoDTO;
import com.zxl.support.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "${seckill.support.feign.name:seckill-support}",
        contextId = "support.ProductExportService"
)
public interface ProductFacadeService {

    /**
     * 创建商品
     * @param productInfoDTO
     * @return
     */
    @PostMapping(value = "/seckill/support/product/createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Integer> createProduct(ProductInfoDTO productInfoDTO);


    /**
     * 查询商品
     * @param productId
     * @return
     */
    @GetMapping(value = "/seckill/support/product/queryProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<ProductInfoDTO> queryProduct(@RequestParam("productId") String productId);

}
