package com.codelucky.spzx.product.service.impl;

import com.codelucky.spzx.model.entity.product.ProductSku;
import com.codelucky.spzx.product.mapper.ProductSkuMapper;
import com.codelucky.spzx.product.service.ProductSkuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSkuServiceImpl implements ProductSkuService {

    @Resource
    private ProductSkuMapper productSkuMapper;

    /**
     * 获取畅销商品列表
     *
     * @return
     */
    @Override
    public List<ProductSku> findProductSkuBySale() {
        List<ProductSku> productSkuList = productSkuMapper.findProductSkuBySale();
        return productSkuList;
    }
}
