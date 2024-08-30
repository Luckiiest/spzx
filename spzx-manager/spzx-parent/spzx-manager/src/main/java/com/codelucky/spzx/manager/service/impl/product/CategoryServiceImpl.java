package com.codelucky.spzx.manager.service.impl.product;

import com.alibaba.excel.EasyExcel;
import com.codelucky.spzx.manager.listener.CategoryExcelListener;
import com.codelucky.spzx.manager.mapper.product.CategoryMapper;
import com.codelucky.spzx.manager.service.product.CategoryService;
import com.codelucky.spzx.model.entity.product.Category;
import com.codelucky.spzx.model.vo.product.CategoryExcelVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    
    /**
     根据父级id查询下属子集分类列表
     @param parentId
     @return
     */
    @Override
    public List<Category> findCategoryListByParentId(Long parentId) {
        // 根据父级id查询下属子集分类列表
        List<Category> categoryList = categoryMapper.findCategoryListByParentId(parentId);
        //判断其中每个分类是否有下级分类
        for (Category category : categoryList) {
            // 根据当前分类的id，去统计子集分类的数量
            Long count = categoryMapper.countByParentId(category.getId());
            if (count.intValue() <= 0) {
                category.setHasChildren(false);
            } else {
                category.setHasChildren(true);
            }
        }
        return categoryList;
    }
    
    /**
     分类管理数据导出excel
     注意：此时并不涉及到监听器回调
     涉及的API：
     EasyExcel.write(response.getOutputStream(),DownloadData.class).sheet("模板").doWrite(list)
     参数1：响应流对象
     参数2：实体类字节码
     参数3：sheet名称
     参数4：list集合数据
     @param response
     */
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            // 相应数据到浏览器的设置
            // 设置响应的mine类型
            response.setContentType("application/vnd.ms-excel");
            // 设置服务器响应内容按照UTF-8进行编码
            response.setCharacterEncoding("UTF-8");
            // 设置中文乱码
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "utf-8");
            // 设置浏览器头，按照下载的方式进行处理
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            
            
            // 查询所有分类
            List<Category> categoryList = categoryMapper.selectAllCategory();
            // 转换数据为CategoryExcelVo
            List<CategoryExcelVo> categoryExcelVoList = categoryList.stream()
                                                                    .map(category -> {
                                                                        // 创建excel实体类
                                                                        CategoryExcelVo categoryExcelVo =
                                                                                new CategoryExcelVo();
                                                                        // 拷贝数据
                                                                        BeanUtils.copyProperties(category,
                                                                                                 categoryExcelVo);
                                                                        return categoryExcelVo;
                                                                    })
                                                                    .collect(Collectors.toList());
            
            // 写出数据
            
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                     .sheet("分类数据")
                     .doWrite(categoryExcelVoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     分类管理数据导入excel
     EasyExcel 读取
     1. 定义实体类
     CategoryExcelVo
     2. 定义监听器回调
     3. 读取
     4. 写入数据库
     */
    @Override
    public void importData(MultipartFile file) {
        
        try {
            // 先删除所有数据
            categoryMapper.deleteData();
            // 创建监听器回调
            CategoryExcelListener categoryExcelListener = new CategoryExcelListener(categoryMapper);
            EasyExcel.read(file.getInputStream(), CategoryExcelVo.class, new CategoryExcelListener(categoryMapper))
                     .sheet()
                     .doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
}
