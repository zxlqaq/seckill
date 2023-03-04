package com.zxl.support.facade.impl;

import com.zxl.support.ProductFacadeService;
import com.zxl.support.ProductService;
import com.zxl.support.dto.ProductInfoDTO;
import com.zxl.support.dto.Result;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductExportServiceImpl implements ProductFacadeService {

    Logger logger = LogManager.getLogger(ProductExportServiceImpl.class);

    private final ProductService productService;

    @Override
    public Result<Integer> createProduct(ProductInfoDTO productInfoDTO) {
        return null;
    }

    @Override
    public Result<ProductInfoDTO> queryProduct(String productId) {
        return productService.queryProduct(productId);
    }
}
