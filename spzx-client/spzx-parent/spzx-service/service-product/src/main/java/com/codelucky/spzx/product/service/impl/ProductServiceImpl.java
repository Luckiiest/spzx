package com.codelucky.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.codelucky.spzx.model.dto.h5.ProductSkuDto;
import com.codelucky.spzx.model.dto.product.SkuSaleDto;
import com.codelucky.spzx.model.entity.product.Product;
import com.codelucky.spzx.model.entity.product.ProductDetails;
import com.codelucky.spzx.model.entity.product.ProductSku;
import com.codelucky.spzx.model.vo.h5.ProductItemVo;
import com.codelucky.spzx.product.mapper.ProductDetailsMapper;
import com.codelucky.spzx.product.mapper.ProductMapper;
import com.codelucky.spzx.product.mapper.ProductSkuMapper;
import com.codelucky.spzx.product.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductSkuMapper productSkuMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductDetailsMapper productDetailsMapper;

    /**
     * 分页查询商品列表
     *
     * @param page
     * @param limit
     * @param productSkuDto
     * @return
     */
    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page, limit);
        List<ProductSku> productSkuList = productSkuMapper.findByPage(productSkuDto);
        return new PageInfo<>(productSkuList);
    }

    /**
     * 商品详情接口
     *
     * @param skuId
     * @return
     */
    @Override
    public ProductItemVo getProductItem(Long skuId) {
        // 获取商品SKU详情信息
        ProductSku productSku = productSkuMapper.getById(skuId);
        // 查询商品spu信息
        Product product = productMapper.getById(productSku.getProductId());
        // 查询商品详情
        ProductDetails productDetails = productDetailsMapper.findByProductId(productSku.getProductId());
        // 根据product_id查询这一组sku
        List<ProductSku> productSkuList = productSkuMapper.findByProductId(productSku.getProductId());
        // 所有的规格 组成map数据,key 规格,value skuId
        Map<String, Object> skuSpecValueMap = new HashMap<>();
        for (ProductSku sku : productSkuList) {
            skuSpecValueMap.put(sku.getSkuSpec(), sku.getId());
        }

        // 封装详情页数据 ProductItemVo
        ProductItemVo productItemVo = new ProductItemVo();
        // 封装商品sku
        productItemVo.setProductSku(productSku);
        // 封装商品spu
        productItemVo.setProduct(product);
        // 封装轮播图
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        // 封装详情
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        // 封装spu的商品规格
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);
        return productItemVo;
    }

    /**
     * 根据skuId查询sku商品信息
     *
     * @param skuId
     * @return
     */
    @Override
    public ProductSku getBySkuId(Long skuId) {
        return productSkuMapper.getById(skuId);
    }

    /**
     * 更新商品sku销量
     *
     * @param skuSaleDtoList
     * @return
     */
    @Transactional
    @Override
    public Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList) {
        if (!CollectionUtils.isEmpty(skuSaleDtoList)) {
            for (SkuSaleDto skuSaleDto : skuSaleDtoList) {
                productSkuMapper.updateSale(skuSaleDto.getSkuId(), skuSaleDto.getNum());
            }
        }
        return true;
    }
}
