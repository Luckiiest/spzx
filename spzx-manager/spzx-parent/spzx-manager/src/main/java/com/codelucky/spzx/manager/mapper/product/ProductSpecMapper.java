package com.codelucky.spzx.manager.mapper.product;

import com.codelucky.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSpecMapper {
    
    /**
     分页查询商品规格
     @return
     */
    List<ProductSpec> findByPage();
    
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
