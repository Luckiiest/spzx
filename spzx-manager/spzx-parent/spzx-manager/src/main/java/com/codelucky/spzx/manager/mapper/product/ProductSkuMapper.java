package com.codelucky.spzx.manager.mapper.product;

import com.codelucky.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductSkuMapper {
    /**
     添加商品Sku
     @param productSkuList
     */
    void save(ProductSku productSkuList);
    
    /**
     根据商品id查询sku数据
     @param id
     @return
     */
    List<ProductSku> selectByProductId(Long id);
    
    /**
     保存修改productSku信息
     @param productSku
     */
    void updateById(ProductSku productSku);
    
    /**
     根据产品id删除productSku
     @param id
     */
    void deleteByProductId(Long id);
    
    /**
     根据商品id对商品sku进行上下架
     @param id
     */
    void updateByProductId(@Param("productId") Long productId, @Param("status") Integer status);
}
