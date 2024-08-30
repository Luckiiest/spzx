package com.codelucky.spzx.manager.mapper.product;

import com.codelucky.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailsMapper {
    /**
     保存商品详情图片
     @param productDetails
     */
    void save(ProductDetails productDetails);
    
    /**
     根据商品id查询sku数据
     @param id
     @return
     */
    ProductDetails selectByProductId(Long id);
    
    /**
     保存修改productDetail信息
     @param productDetails
     */
    void updateById(ProductDetails productDetails);
    
    /**
     根据产品id删除productDetails
     * @param id
     */
    void deleteByProductId(Long id);
}
