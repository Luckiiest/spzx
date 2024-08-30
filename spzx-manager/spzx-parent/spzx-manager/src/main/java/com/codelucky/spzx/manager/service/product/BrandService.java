package com.codelucky.spzx.manager.service.product;

import com.codelucky.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    /**
     查询品牌分页查询
     @param pageNum
     @param pageSize
     @return
     */
    PageInfo<Brand> findByPage(int pageNum, int pageSize);
    
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
     根据id删除品牌数据
     @param id
     */
    void deleteById(Long id);
    
    /**
     查询所有
     @return
     */
    List<Brand> findAll();
}
