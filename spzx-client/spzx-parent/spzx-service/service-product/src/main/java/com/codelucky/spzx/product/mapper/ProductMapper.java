package com.codelucky.spzx.product.mapper;

import com.codelucky.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    /**
     * 查询商品spu信息
     *
     * @param productId
     * @return
     */
    Product getById(Long productId);
}
