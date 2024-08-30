package com.codelucky.spzx.manager.controller.product;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.product.ProductService;
import com.codelucky.spzx.model.dto.product.ProductDto;
import com.codelucky.spzx.model.entity.product.Product;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/product/product")
@Tag(name = "商品管理")
public class ProductController {
    
    @Resource
    private ProductService productService;
    
    /**
     根据商品Id对商品进行上下架
     @param id
     @param status
     @return
     */
    @GetMapping("/updateStatus/{id}/{status}")
    @Log(title = "商品列表模块-根据商品Id对商品进行上下架",
         businessType = 2)
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id, status);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     根据商品id进行审核
     @param id
     @param auditStatus
     @return
     */
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    @Log(title = "商品列表模块-根据商品id进行审核",
         businessType = 2)
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     根据id删除商品信息
     @param id
     @return
     */
    @DeleteMapping("/deleteById/{id}")
    @Log(title = "商品列表模块-根据id删除商品信息",
         businessType = 3)
    public Result deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     修改保存商品信息
     @param product
     @return
     */
    @PutMapping("/updateById")
    @Log(title = "商品列表模块-修改商品信息",
         businessType = 2)
    public Result updateById(@RequestBody Product product) {
        productService.updateById(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     根据商品Id查询商品信息(包括Sku和Details)
     @param id
     @return
     */
    @GetMapping("/getById/{id}")
    @Log(title = "商品列表模块-根据商品Id查询商品信息",
         businessType = 0,
         isSaveResponseData = false)
    public Result<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return Result.build(product, ResultCodeEnum.SUCCESS);
    }
    
    /**
     保存商品
     @param product
     @return
     */
    @PostMapping("/save")
    @Log(title = "商品列表模块-保存商品",
         businessType = 1)
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     条件分页查询列表
     @param pageNum
     @param pageSize
     @param productDto
     @return
     */
    @GetMapping("/{pageNum}/{pageSize}")
    @Log(title = "商品列表模块-条件分页查询",
         businessType = 0,
         isSaveResponseData = false)
    public Result<PageInfo<Product>> findByPage(@PathVariable int pageNum, @PathVariable int pageSize,
                                                ProductDto productDto) {
        PageInfo<Product> productPageInfo = productService.findByPage(pageNum, pageSize, productDto);
        return Result.build(productPageInfo, ResultCodeEnum.SUCCESS);
    }
    
}
