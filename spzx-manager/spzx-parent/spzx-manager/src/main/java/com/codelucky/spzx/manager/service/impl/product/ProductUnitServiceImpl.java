package com.codelucky.spzx.manager.service.impl.product;

import com.codelucky.spzx.manager.mapper.product.ProductUnitMapper;
import com.codelucky.spzx.manager.service.product.ProductUnitService;
import com.codelucky.spzx.model.entity.base.ProductUnit;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    
    @Resource
    private ProductUnitMapper productUnitMapper;
    
    /**
     /**
     查询商品单元数据
     @return
     */
    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll();
    }
}
