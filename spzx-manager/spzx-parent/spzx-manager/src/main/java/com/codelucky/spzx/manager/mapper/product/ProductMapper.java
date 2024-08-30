package com.codelucky.spzx.manager.mapper.product;

import com.codelucky.spzx.model.dto.product.ProductDto;
import com.codelucky.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     查询商品列表
     @param productDto
     @return
     */
    List<Product> findByPage(ProductDto productDto);
    
    /**
     保存当前商品信息
     @param product
     */
    void save(Product product);
    
    /**
     根据id查询商品对象
     @param id
     @return
     */
    Product selectByProductId(Long id);
    
    /**
     修改商品信息
     @param product
     */
    void updateById(Product product);
    
    /**
     根据产品id删除product
     @param id
     */
    void deleteById(Long id);
}
