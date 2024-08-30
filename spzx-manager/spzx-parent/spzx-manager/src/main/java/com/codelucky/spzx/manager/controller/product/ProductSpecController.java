package com.codelucky.spzx.manager.controller.product;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.product.ProductSpecService;
import com.codelucky.spzx.model.entity.product.ProductSpec;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product/productSpec")
@Tag(name = "商品规格")
public class ProductSpecController {
    @Resource
    private ProductSpecService productSpecService;
    
    /**
     查询全部规格数据
     @return
     */
    @GetMapping("/findAll")
    @Log(title = "商品规格模块-查询全部",
         businessType = 0,
         isSaveResponseData = false)
    public Result findAll() {
        List<ProductSpec> list = productSpecService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
    
    /**
     分页查询商品规格
     @param pageNum
     @param pageSize
     @return
     */
    @GetMapping("/{pageNum}/{pageSize}")
    @Log(title = "商品规格模块-分页查询",
         businessType = 0,
         isSaveResponseData = false)
    public Result<PageInfo<ProductSpec>> findByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageInfo<ProductSpec> pageInfo = productSpecService.findByPage(pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
    
    /**
     新增商品规格
     @param productSpec
     @return
     */
    @PostMapping("save")
    @Log(title = "商品规格模块-新增商品",
         businessType = 1)
    public Result save(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     修改商品规格
     @param productSpec
     @return
     */
    @PutMapping("updateById")
    @Log(title = "商品规格模块-修改商品规格",
         businessType = 2)
    public Result updateById(@RequestBody ProductSpec productSpec) {
        productSpecService.updateById(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     删除商品规格
     @param id
     @return
     */
    @DeleteMapping("/deleteById/{id}")
    @Log(title = "商品规格模块-删除商品规格",
         businessType = 3)
    public Result removeById(@PathVariable Long id) {
        productSpecService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
