package com.codelucky.spzx.product.mapper;

import com.codelucky.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailsMapper {
    /**
     * 查询商品详情
     * ProductDetails findByProductId(Long productId);
     *
     * @param productId
     * @return
     */
    ProductDetails findByProductId(Long productId);
}
