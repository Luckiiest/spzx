package com.codelucky.spzx.product.service;

import com.codelucky.spzx.model.entity.product.ProductSku;

import java.util.List;

public interface ProductSkuService {
    /**
     * 获取畅销商品列表
     *
     * @return
     */
    List<ProductSku> findProductSkuBySale();
}
