package com.codelucky.spzx.manager.service.impl.product;

import com.codelucky.spzx.manager.mapper.product.ProductSpecMapper;
import com.codelucky.spzx.manager.service.product.ProductSpecService;
import com.codelucky.spzx.model.entity.product.ProductSpec;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    
    @Resource
    private ProductSpecMapper productSpecMapper;
    
    /**
     分页查询商品规格
     @param pageNum
     @param pageSize
     @return
     */
    @Override
    public PageInfo<ProductSpec> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductSpec> productSpecList = productSpecMapper.findByPage();
        return new PageInfo<>(productSpecList);
    }
    
    /**
     新增商品规格
     @param productSpec
     */
    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }
    
    /**
     修改商品规格
     @param productSpec
     */
    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }
    
    /**
     删除商品规格
     @param id
     */
    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id);
    }
    
    /**
     查询全部规格数据
     @return
     */
    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.findAll();
    }
}
