package com.codelucky.spzx.manager.service.impl.product;

import com.codelucky.spzx.manager.mapper.product.BrandMapper;
import com.codelucky.spzx.manager.service.product.BrandService;
import com.codelucky.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    
    @Resource
    private BrandMapper brandMapper;
    
    /**
     查询品牌分页查询
     @param pageNum
     @param pageSize
     @return
     */
    @Override
    public PageInfo<Brand> findByPage(int pageNum, int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 查询数据
        List<Brand> brandList = brandMapper.findByPage();
        // 封装数据
        PageInfo<Brand> pageInfo = new PageInfo(brandList);
        
        return pageInfo;
    }
    
    /**
     新增品牌数据
     @param brand
     */
    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }
    
    /**
     修改品牌数据
     @param brand
     */
    @Override
    public void updateById(Brand brand) {
        brandMapper.updateById(brand);
    }
    
    /**
     根据id删除品牌数据
     @param id
     */
    @Override
    public void deleteById(Long id) {
        brandMapper.deleteById(id);
    }
    
    /**
     查询所有
     @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
