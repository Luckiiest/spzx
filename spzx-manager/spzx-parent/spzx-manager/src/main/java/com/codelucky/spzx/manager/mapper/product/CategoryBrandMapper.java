package com.codelucky.spzx.manager.mapper.product;

import com.codelucky.spzx.model.dto.product.CategoryBrandDto;
import com.codelucky.spzx.model.entity.product.Brand;
import com.codelucky.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryBrandMapper {
    
    /**
     查询分类品牌列表
     @param categoryBrandDto
     @return
     */
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);
    
    /**
     新增品牌分类
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
     * @param categoryId
     * @return
     */
    List<Brand> findBrandByCategoryId(Integer categoryId);
}
