package com.codelucky.spzx.manager.controller.product;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.product.BrandService;
import com.codelucky.spzx.model.entity.product.Brand;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product/brand")
@Tag(name = "品牌管理")
public class BrandController {
    
    @Resource
    private BrandService brandService;
    
    
    /**
     查询全部
     @return
     */
    @GetMapping("/findAll")
    @Log(title = "商品品牌模块-查询全部",
         businessType = 0,
         isSaveResponseData = false)
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
    
    /**
     根据id删除品牌数据
     @param id
     @return
     */
    @DeleteMapping("/deleteById/{id}")
    @Log(title = "商品品牌模块-根据id删除",
         businessType = 3)
    public Result deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     修改品牌数据
     @param brand
     @return
     */
    @PutMapping("/updateById")
    @Log(title = "商品品牌模块-修改方法",
         businessType = 2)
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     新增数据
     */
    @PostMapping("/save")
    @Log(title = "商品品牌模块-新增方法",
         businessType = 1)
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     查询品牌分页查询
     @param pageNum
     @param pageSize
     @return
     */
    @GetMapping("/{pageNum}/{pageSize}")
    @Log(title = "商品品牌模块-查询方法",
         businessType = 0,
         isSaveResponseData = false)
    public Result<PageInfo<Brand>> findByPage(@PathVariable("pageNum") int pageNum,
                                              @PathVariable("pageSize") int pageSize) {
        PageInfo<Brand> pageInfo = brandService.findByPage(pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
