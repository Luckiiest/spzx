package com.codelucky.spzx.manager.mapper.product;

import com.codelucky.spzx.model.entity.product.Category;
import com.codelucky.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     根据父级id查询下属子集分类列表
     @param parentId
     @return
     */
    List<Category> findCategoryListByParentId(Long parentId);
    
    /**
     根据当前分类的id，去统计子集分类的数量
     @param id
     @return
     */
    Long countByParentId(Long id);
    
    /**
     查询全部数据
     @return
     */
    List<Category> selectAllCategory();
    
    /**
     批量插入excel数据
     @param cachedDataList
     */
    void batchInsert(List<CategoryExcelVo> cachedDataList);
    
    /**
     删除表中数据
     */
    void deleteData();
}
