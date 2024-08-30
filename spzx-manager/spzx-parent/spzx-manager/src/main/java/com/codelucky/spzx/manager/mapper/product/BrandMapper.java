package com.codelucky.spzx.manager.mapper.product;

import com.codelucky.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    
    /**
     查询品牌管理数据
     @return
     */
    List<Brand> findByPage();
    
    /**
     新增品牌数据
     @param brand
     */
    void save(Brand brand);
    
    /**
     修改品牌数据
     @param brand
     */
    void updateById(Brand brand);
    
    /**
     根据id删除
     @param id
     */
    void deleteById(Long id);
    
    /**
     查询所有
     @return
     */
    List<Brand> findAll();
}
