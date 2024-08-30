package com.codelucky.spzx.product.mapper;

import com.codelucky.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    /**
     * 获取全部品牌
     *
     * @return
     */
    List<Brand> findAll();
}
