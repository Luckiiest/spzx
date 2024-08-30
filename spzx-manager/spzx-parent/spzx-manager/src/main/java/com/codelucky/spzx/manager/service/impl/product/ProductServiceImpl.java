package com.codelucky.spzx.manager.service.impl.product;

import com.codelucky.spzx.manager.mapper.product.ProductDetailsMapper;
import com.codelucky.spzx.manager.mapper.product.ProductMapper;
import com.codelucky.spzx.manager.mapper.product.ProductSkuMapper;
import com.codelucky.spzx.manager.service.product.ProductService;
import com.codelucky.spzx.model.dto.product.ProductDto;
import com.codelucky.spzx.model.entity.product.Product;
import com.codelucky.spzx.model.entity.product.ProductDetails;
import com.codelucky.spzx.model.entity.product.ProductSku;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductSkuMapper productSkuMapper;
    @Resource
    private ProductDetailsMapper productDetailsMapper;
    
    /**
     条件分页查询列表
     @param pageNum
     @param pageSize
     @param productDto
     @return
     */
    @Override
    public PageInfo<Product> findByPage(int pageNum, int pageSize, ProductDto productDto) {
        PageHelper.startPage(pageNum, pageSize);
        //根据条件分页查询商品列表
        List<Product> productList = productMapper.findByPage(productDto);
        return new PageInfo<>(productList);
    }
    
    
    /**
     保存商品
     涉及到几张表
     product
     product_details
     procut_sku
     @param product
     @Transactional 特点: 默认只能拦截运行时异常 RuntimeException
     但是 IOException不在RuntimeException体系下
     处理扩大异常范围
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Product product) {
        // 保存product
        product.setStatus(0);
        product.setAuditStatus(0);
        productMapper.save(product);
        
        // 保存product_detail
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);
        
        // 保存product_sku
        List<ProductSku> productSkuList = product.getProductSkuList();
        // 遍历设置数据
        // 定义索引遍历
        AtomicInteger index = new AtomicInteger();
        productSkuList.forEach(productSku -> {
            // 生成sku_code
            productSku.setSkuCode(product.getId() + "_" + index.get());
            // 生成sku_name
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            // 设置product_id
            productSku.setProductId(product.getId());
            // 设置销量
            productSku.setSaleNum(0);
            // 设置状态
            productSku.setStatus(0);
            index.getAndIncrement();
            productSkuMapper.save(productSku);
        });
    }
    
    /**
     根据商品Id查询商品信息(包括Sku和Details)
     @param id
     @return
     */
    @Override
    public Product getProductById(Long id) {
        // 根据id查询商品对象
        Product product = productMapper.selectByProductId(id);
        // 根据商品id查询sku数据
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(id);
        product.setProductSkuList(productSkuList);
        // 根据商品id查询detail数据
        ProductDetails productDetails = productDetailsMapper.selectByProductId(id);
        product.setDetailsImageUrls(productDetails.getImageUrls());
        return product;
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(Product product) {
        // 保存修改product商品信息
        productMapper.updateById(product);
        // 保存修改productSku信息
        List<ProductSku> productSkuList = product.getProductSkuList();
        if (CollectionUtils.isEmpty(productSkuList)) {
            productSkuList.forEach(productSku -> {
                productSkuMapper.updateById(productSku);
            });
        }
        // 保存修改productDetail信息
        ProductDetails productDetails = productDetailsMapper.selectByProductId(product.getId());
        String detailsImageUrls = product.getDetailsImageUrls();
        productDetails.setImageUrls(detailsImageUrls);
        productDetailsMapper.updateById(productDetails);
    }
    
    /**
     根据id删除商品信息
     @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        // 根据产品id删除product
        productMapper.deleteById(id);
        // 根据产品id删除productSku
        productSkuMapper.deleteByProductId(id);
        // 根据产品id删除productDetails
        productDetailsMapper.deleteByProductId(id);
    }
    
    /**
     根据商品id进行审核
     @param id
     @param auditStatus
     */
    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        product.setAuditStatus(auditStatus);
        if (auditStatus == 1) {
            product.setAuditMessage("审核通过");
        } else {
            product.setAuditMessage("审核不通过");
        }
        productMapper.updateById(product);
    }
    
    /**
     根据商品Id对商品进行上下架
     @param id
     @param status
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(status);
        // 对商品进行上下架
        productMapper.updateById(product);
        // 对商品sku上下架
        /*
        1. 第一种方案
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(id);
        productSkuList.forEach(productSku -> {
            productSku.setStatus(status);
            productSkuMapper.updateById(productSku);
        });*/
        /*2. 第二种方案 使用sql*/
        productSkuMapper.updateByProductId(id, status);
    }
}
