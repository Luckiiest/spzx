package com.codelucky.spzx.manager.service.product;

import com.codelucky.spzx.model.dto.product.CategoryBrandDto;
import com.codelucky.spzx.model.entity.product.Brand;
import com.codelucky.spzx.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryBrandSerivce {
    
    /**
     分页条件查询分类品牌列表
     @param pageNum
     @param pageSize
     @param categoryBrandDto
     @return
     */
    PageInfo<CategoryBrand> findByPage(Integer pageNum, Integer pageSize, CategoryBrandDto categoryBrandDto);
    
    /**
     新增分类品牌
     @param categoryBrand
     */
    void save(CategoryBrand categoryBrand);
    
    /**
     修改分类品牌
     @param categoryBrand
     */
    void updateById(CategoryBrand categoryBrand);
    
    /**
     删除品牌分类
     @param id
     */
    void deleteById(Long id);
    
    /**
     根据分类查询品牌列表
     @param categoryId
     @return
     */
    List<Brand> findBrandByCategoryId(Integer categoryId);
}
