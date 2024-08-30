package com.codelucky.spzx.manager.service.product;

import com.codelucky.spzx.model.dto.product.ProductDto;
import com.codelucky.spzx.model.entity.product.Product;
import com.github.pagehelper.PageInfo;

public interface ProductService {
    /**
     条件分页查询列表
     @param pageNum
     @param pageSize
     @param productDto
     @return
     */
    PageInfo<Product> findByPage(int pageNum, int pageSize, ProductDto productDto);
    
    /**
     保存商品
     涉及到几张表
     product
     product_details
     procut_sku
     @param product
     */
    void save(Product product);
    
    /**
     根据商品Id查询商品信息(包括Sku和Details)
     @param id
     @return
     */
    Product getProductById(Long id);
    
    /**
     修改保存商品信息
     @param product
     */
    void updateById(Product product);
    
    /**
     根据id删除商品信息
     @param id
     */
    void deleteById(Long id);
    
    /**
     根据商品id进行审核
     @param id
     @param auditStatus
     */
    void updateAuditStatus(Long id, Integer auditStatus);
    
    /**
     根据商品Id对商品进行上下架
     @param id
     @param status
     */
    void updateStatus(Long id, Integer status);
}
