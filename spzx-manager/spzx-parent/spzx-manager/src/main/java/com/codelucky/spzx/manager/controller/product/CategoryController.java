package com.codelucky.spzx.manager.controller.product;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.product.CategoryService;
import com.codelucky.spzx.model.entity.product.Category;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/product/category")
@Tag(name = "分类管理")
public class CategoryController {
    
    @Resource
    private CategoryService categoryService;
    
    /**
     分类管理数据导出excel
     */
    @GetMapping("/exportData")
    @Log(title = "商品分类模块-数据导出excel",
         businessType = 0,
         isSaveResponseData = false)
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }
    
    /**
     导入excel到分类管理数据
     */
    @PostMapping("/importData")
    @Log(title = "商品分类模块-excel数据导入",
         businessType = 1)
    public Result importData(MultipartFile file) {
        categoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     根据父级id查询下属子集分类列表
     @return
     */
    @GetMapping("/findCategoryListByParentId/{parentId}")
    @Log(title = "商品分类模块-根据父类id查询子集分类",
         businessType = 0,
         isSaveResponseData = false)
    public Result<List<Category>> findCategoryListByParentId(@PathVariable Long parentId) {
        List<Category> categoryList = categoryService.findCategoryListByParentId(parentId);
        return Result.build(categoryList, ResultCodeEnum.SUCCESS);
    }
}
