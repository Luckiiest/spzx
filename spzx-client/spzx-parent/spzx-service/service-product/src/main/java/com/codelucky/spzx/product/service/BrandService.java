package com.codelucky.spzx.product.service;

import com.codelucky.spzx.model.entity.product.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 获取全部品牌
     *
     * @return
     */
    List<Brand> findAll();
}
