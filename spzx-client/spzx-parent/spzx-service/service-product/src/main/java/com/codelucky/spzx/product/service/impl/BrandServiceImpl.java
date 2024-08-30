package com.codelucky.spzx.product.service.impl;

import com.codelucky.spzx.model.entity.product.Brand;
import com.codelucky.spzx.product.mapper.BrandMapper;
import com.codelucky.spzx.product.service.BrandService;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    /**
     * 获取全部品牌
     *
     * @return
     */
    @Cacheable(value = "brandList", unless = "#result.size() == 0")
    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
