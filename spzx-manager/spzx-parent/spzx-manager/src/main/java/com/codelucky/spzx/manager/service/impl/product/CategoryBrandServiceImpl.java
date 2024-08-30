package com.codelucky.spzx.manager.service.impl.product;

import com.codelucky.spzx.manager.mapper.product.CategoryBrandMapper;
import com.codelucky.spzx.manager.service.product.CategoryBrandSerivce;
import com.codelucky.spzx.model.dto.product.CategoryBrandDto;
import com.codelucky.spzx.model.entity.product.Brand;
import com.codelucky.spzx.model.entity.product.CategoryBrand;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.service.exception.CodeLuckyException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryBrandServiceImpl implements CategoryBrandSerivce {
    
    @Resource
    private CategoryBrandMapper categoryBrandMapper;
    
    /**
     分页条件查询分类品牌列表
     @param pageNum
     @param pageSize
     @param categoryBrandDto
     @return
     */
    @Override
    public PageInfo<CategoryBrand> findByPage(Integer pageNum, Integer pageSize, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(pageNum, pageSize);
        List<CategoryBrand> categoryBrandList = categoryBrandMapper.findByPage(categoryBrandDto);
        return new PageInfo<>(categoryBrandList);
    }
    
    /**
     新增品牌分类
     @param categoryBrand
     */
    @Override
    public void save(CategoryBrand categoryBrand) {
        //根据categoryId和brandId作为条件查询数据
        CategoryBrandDto categoryBrandDto = new CategoryBrandDto();
        categoryBrandDto.setCategoryId(categoryBrand.getCategoryId());
        categoryBrandDto.setBrandId(categoryBrand.getBrandId());
        List<CategoryBrand> brandList = categoryBrandMapper.findByPage(categoryBrandDto);
        // 判断
        if (!CollectionUtils.isEmpty(brandList)) {
            throw new CodeLuckyException(ResultCodeEnum.CATEGORY_BRAND_RES_IS_EXISTS);
        }
        
        categoryBrandMapper.save(categoryBrand);
    }
    
    /**
     修改分类品牌
     @param categoryBrand
     */
    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand);
    }
    
    /**
     删除品牌分类
     @param id
     */
    @Override
    public void deleteById(Long id) {
        categoryBrandMapper.deleteById(id);
    }
    
    /**
     根据分类查询品牌列表
     @param categoryId
     @return
     */
    @Override
    public List<Brand> findBrandByCategoryId(Integer categoryId) {
        
        List<Brand> brandList = categoryBrandMapper.findBrandByCategoryId(categoryId);
        return brandList;
    }
}
