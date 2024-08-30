package com.codelucky.spzx.manager.controller.product;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.product.CategoryBrandSerivce;
import com.codelucky.spzx.model.dto.product.CategoryBrandDto;
import com.codelucky.spzx.model.entity.product.Brand;
import com.codelucky.spzx.model.entity.product.CategoryBrand;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product/categoryBrand")
@Tag(name = "分类品牌")
public class CategoryBrandController {
    
    @Resource
    private CategoryBrandSerivce categoryBrandService;
    
    /**
     根据分类查询品牌列表
     @param categoryId
     @return
     */
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    @Log(title = "商品分类品牌模块-根据分类Id查询品牌列表",
         businessType = 0,
         isSaveResponseData = false)
    public Result<List<Brand>> findBrandByCategoryId(@PathVariable Integer categoryId) {
        List<Brand> brandList = categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(brandList, ResultCodeEnum.SUCCESS);
    }
    
    /**
     分页条件查询分类品牌列表
     @param pageNum
     @param pageSize
     @param categoryBrandDto
     @return
     */
    @GetMapping("/{pageNum}/{pageSize}")
    @Log(title = "商品分类品牌模块-分页查询",
         businessType = 0,
         isSaveResponseData = false)
    public Result<PageInfo<CategoryBrand>> findByPage(@PathVariable("pageNum") Integer pageNum,
                                                      @PathVariable("pageSize") Integer pageSize,
                                                      CategoryBrandDto categoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(pageNum, pageSize, categoryBrandDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
    
    /**
     新增品牌分类
     @param categoryBrand
     @return
     */
    @PostMapping("/save")
    @Log(title = "商品分类品牌模块-新增分类品牌",
         businessType = 1)
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     修改品牌分类
     @param categoryBrand
     @return
     */
    @PutMapping("updateById")
    @Log(title = "商品分类品牌模块-修改分类品牌",
         businessType = 2)
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     删除品牌分类
     @param id
     @return
     */
    @DeleteMapping("/deleteById/{id}")
    @Log(title = "商品分类品牌模块-根据id删除分类品牌",
         businessType = 3)
    public Result deleteById(@PathVariable Long id) {
        categoryBrandService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
