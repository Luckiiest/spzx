package com.codelucky.spzx.product.service;

import com.codelucky.spzx.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 获取一级分类数据
     *
     * @return
     */
    List<Category> findOneCategory();

    /**
     * 获取分类树形数据
     *
     * @return
     */
    List<Category> findCategoryTree();
}
