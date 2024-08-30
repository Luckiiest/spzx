package com.codelucky.spzx.manager.service.product;

import com.codelucky.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductSpecService {
    /**
     分页查询商品规格
     @param pageNum
     @param pageSize
     @return
     */
    PageInfo<ProductSpec> findByPage(Integer pageNum, Integer pageSize);
    
    /**
     新增商品规格
     @param productSpec
     */
    void save(ProductSpec productSpec);
    
    /**
     修改商品规格
     @param productSpec
     */
    void updateById(ProductSpec productSpec);
    
    /**
     删除商品规格
     @param id
     */
    void deleteById(Long id);
    
    /**
     查询全部规格数据
     @return
     */
    List<ProductSpec> findAll();
}
