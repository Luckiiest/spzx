package com.codelucky.spzx.product.mapper;

import com.codelucky.spzx.model.dto.h5.ProductSkuDto;
import com.codelucky.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {
    /**
     * 获取畅销商品列表
     *
     * @return
     */
    List<ProductSku> findProductSkuBySale();

    /**
     * 分页查询商品列表
     *
     * @param productSkuDto
     * @return
     */
    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    /**
     * 根据id获取商品SKU详情信息
     *
     * @param skuId
     * @return
     */
    ProductSku getById(Long skuId);

    /**
     * 根据product_id查询这一组sku
     *
     * @param productId
     * @return
     */
    List<ProductSku> findByProductId(Long productId);

    /**
     * 更新商品sku销量
     *
     * @param skuId
     * @param num
     */
    void updateSale(Long skuId, Integer num);
}
