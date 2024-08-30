package com.codelucky.spzx.product.controller;

import com.codelucky.spzx.model.dto.h5.ProductSkuDto;
import com.codelucky.spzx.model.dto.product.SkuSaleDto;
import com.codelucky.spzx.model.entity.product.ProductSku;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.model.vo.h5.ProductItemVo;
import com.codelucky.spzx.product.service.ProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品管理列表")
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 更新商品sku销量
     *
     * @param skuSaleDtoList
     * @return
     */
    @PostMapping("updateSkuSaleNum")
    public Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList) {
        return productService.updateSkuSaleNum(skuSaleDtoList);
    }

    /**
     * /api/product/getBySkuId/{skuId}
     * 根据skuId查询sku商品信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/getBySkuId/{skuId}")
    public Result getBySkuId(@PathVariable("skuId") Long skuId) {
        ProductSku productSku = productService.getBySkuId(skuId);
        return Result.build(productSku, ResultCodeEnum.SUCCESS);
    }

    /**
     * 商品详情接口
     *
     * @param skuId
     * @return
     */
    @GetMapping("/item/{skuId}")
    public Result<ProductItemVo> getProductItem(@PathVariable("skuId") Long skuId) {
        ProductItemVo productItemVo = productService.getProductItem(skuId);
        return Result.build(productItemVo, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "分页查询")
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<ProductSku>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.findByPage(page, limit, productSkuDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
