package com.codelucky.spzx.feign.product;

import com.codelucky.spzx.model.dto.product.SkuSaleDto;
import com.codelucky.spzx.model.entity.product.ProductSku;
import com.codelucky.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "service-product")
public interface ProductFeignClient {
    /**
     * 根据sku商品id获取sku商品信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/api/product/getBySkuId/{skuId}")
    Result<ProductSku> getBySkuId(@PathVariable("skuId") Long skuId);

    /**
     * 更新商品sku销量
     *
     * @param skuSaleDtoList
     * @return
     */
    @PostMapping("/api/product/updateSkuSaleNum")
    Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList);
}
