package com.codelucky.spzx.product.service.impl;

import com.codelucky.spzx.model.entity.product.Category;
import com.codelucky.spzx.product.mapper.CategoryMapper;
import com.codelucky.spzx.product.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取一级分类数据
     *
     * @return
     */
    @Override
    @Cacheable(value = "category", key = "'one'")
    public List<Category> findOneCategory() {
        List<Category> categoryList = null;
        //// 从redis获取数据
        //String categoryListJSON = (String) redisTemplate.opsForValue().get("category:one");
        // 判断是否存在
        //if (!StringUtils.isEmpty(categoryListJSON)) {
        //    categoryList = JSON.parseArray(categoryListJSON, Category.class);
        //} else {
        categoryList = categoryMapper.findOneCategory();
        //    redisTemplate.opsForValue().set("category:one", JSON.toJSONString(categoryList), 7, TimeUnit.DAYS);
        //}
        return categoryList;
    }

    @Cacheable(value = "category", key = "'all'")
    @Override
    public List<Category> findCategoryTree() {

        // 查询所有分类
        List<Category> categoryList = categoryMapper.findAll();
        // 处理分类列表-绑定从属关系
        // 获取一级分类列表
        List<Category> categoryOneList = categoryList.stream().filter(category -> {
            return category.getParentId().intValue() == 0;
        }).collect(Collectors.toList());
        // 遍历获取二级分类列表
        // 判断
        if (!StringUtils.isEmpty(categoryOneList)) {
            categoryOneList.stream().forEach(category -> {
                // 遍历所有的分类列表,获取与当前一级分类id相同的parentId
                List<Category> categoryTwoList = categoryList.stream().filter(item -> {
                    return category.getId().intValue() == item.getParentId().intValue();
                }).collect(Collectors.toList());
                // 设置二级分类
                category.setChildren(categoryTwoList);
                // 判断
                if (!CollectionUtils.isEmpty(categoryTwoList)) {
                    categoryTwoList.stream().forEach(category2 -> {
                        // 遍历所有的分类列表,获取与当前一级分类id相同的parentId
                        List<Category> categoryThreeList = categoryList.stream().filter(item -> {
                            return category2.getId().intValue() == item.getParentId().intValue();
                        }).collect(Collectors.toList());
                        // 设置三级级分类
                        category2.setChildren(categoryThreeList);
                    });
                }
            });
        }
        return categoryOneList;
    }
}
