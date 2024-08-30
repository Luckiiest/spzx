package com.codelucky.spzx.manager.controller.product;


import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.product.ProductUnitService;
import com.codelucky.spzx.model.entity.base.ProductUnit;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/product/productUnit")
@Tag(name = "商品单元数据")
public class ProductUnitController {
    
    @Resource
    private ProductUnitService productUnitService;
    
    /**
     查询商品单元数据
     @return
     */
    @GetMapping("/findAll")
    @Log(title = "商品单元模块-查询全部",
         businessType = 0,
         isSaveResponseData = false)
    public Result<List<ProductUnit>> findAll() {
        List<ProductUnit> productUnitList = productUnitService.findAll();
        return Result.build(productUnitList, ResultCodeEnum.SUCCESS);
    }
}