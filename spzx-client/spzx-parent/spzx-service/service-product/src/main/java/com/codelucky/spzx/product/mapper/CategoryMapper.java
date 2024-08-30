package com.codelucky.spzx.product.mapper;

import com.codelucky.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 查询一级分类列表
     * @return
     */
    List<Category> findOneCategory();

    /**
     * 查询所有分类
     * @return
     */
    List<Category> findAll();
}
