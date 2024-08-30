package com.codelucky.spzx.product.controller;

import com.codelucky.spzx.model.entity.product.Category;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product/category")
@Tag(name = "分类接口管理")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 获取分类树形数据
     *
     * @return
     */
    @Operation(summary = "获取分类树形数据")
    @GetMapping("/findCategoryTree")
    public Result<List<Category>> findCategoryTree() {
        List<Category> list = categoryService.findCategoryTree();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

}
