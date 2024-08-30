package com.codelucky.spzx.manager.service.product;

import com.codelucky.spzx.model.entity.base.ProductUnit;

import java.util.List;

public interface ProductUnitService {
    /**
     查询商品单元数据
     @return
     */
    List<ProductUnit> findAll();
}
