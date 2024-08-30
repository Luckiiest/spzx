package com.codelucky.spzx.product.controller;

import com.codelucky.spzx.model.entity.product.Category;
import com.codelucky.spzx.model.entity.product.ProductSku;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.model.vo.h5.IndexVo;
import com.codelucky.spzx.product.service.CategoryService;
import com.codelucky.spzx.product.service.ProductSkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "首页接口管理")
@RestController
@RequestMapping("/api/product")
@SuppressWarnings("all")
public class IndexController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductSkuService productSkuService;

    /**
     * 首页数据查询
     *
     * @return
     */
    @Operation(summary = "获取首页数据")
    @GetMapping("/index")
    public Result<IndexVo> index() {

        // 获取一级分类数据
        List<Category> categoryList = categoryService.findOneCategory();
        // 获取畅销商品列表
        List<ProductSku> productSkuList = productSkuService.findProductSkuBySale();
        // 封装IndexVo
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);

        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }
}
