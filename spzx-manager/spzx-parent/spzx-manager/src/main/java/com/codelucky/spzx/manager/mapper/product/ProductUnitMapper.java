package com.codelucky.spzx.manager.mapper.product;

import com.codelucky.spzx.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductUnitMapper {
    /**
     查询商品单元数据
     @return
     */
    List<ProductUnit> findAll();
}
