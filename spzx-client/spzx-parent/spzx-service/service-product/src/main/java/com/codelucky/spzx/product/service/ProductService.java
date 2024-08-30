package com.codelucky.spzx.product.service;

import com.codelucky.spzx.model.dto.h5.ProductSkuDto;
import com.codelucky.spzx.model.dto.product.SkuSaleDto;
import com.codelucky.spzx.model.entity.product.ProductSku;
import com.codelucky.spzx.model.vo.h5.ProductItemVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {
    /**
     * 分页查询商品列表
     *
     * @param page
     * @param limit
     * @param productSkuDto
     * @return
     */
    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    /**
     * 商品详情接口
     *
     * @param skuId
     * @return
     */
    ProductItemVo getProductItem(Long skuId);

    /**
     * 根据skuId查询sku商品信息
     *
     * @param skuId
     * @return
     */
    ProductSku getBySkuId(Long skuId);

    /**
     * 更新商品sku销量
     *
     * @param skuSaleDtoList
     * @return
     */
    Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList);
}
