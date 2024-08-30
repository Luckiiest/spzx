package com.codelucky.spzx.manager.service.product;

import com.codelucky.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    
    /**
     根据父级id查询下属子集分类列表
     @param parentId
     @return
     */
    List<Category> findCategoryListByParentId(Long parentId);
    
    /**
     分类管理数据导出excel
     @param response
     */
    void exportData(HttpServletResponse response);
    
    /**
     分类管理数据导入excel
     */
    void importData(MultipartFile file);
}
