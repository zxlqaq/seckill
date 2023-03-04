package com.zxl.support.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.zxl.support.ProductService;
import com.zxl.support.dto.ProductInfoDTO;
import com.zxl.support.dto.Result;
import com.zxl.support.entity.ProductInfo;
import com.zxl.support.mapper.ProductInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductInfoMapper productInfoMapper;

    @Override
    public Result<Integer> createProduct(ProductInfoDTO productInfoDTO) {
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productInfoDTO,productInfo);
        int count =  productInfoMapper.insert(productInfo);
        return new Result<>(count);
    }

    @Override
    public Result<ProductInfoDTO> queryProduct(String productId) {
        ProductInfo productInfo = new LambdaQueryChainWrapper<>(productInfoMapper)
                .eq(ProductInfo::getProductId, productId)
                .one();
        ProductInfoDTO infoDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(productInfo,infoDTO);
        return new Result<>(infoDTO);
    }
}
