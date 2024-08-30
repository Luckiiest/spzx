package com.codelucky.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.codelucky.spzx.manager.mapper.product.CategoryMapper;
import com.codelucky.spzx.model.vo.product.CategoryExcelVo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CategoryExcelListener<T> extends AnalysisEventListener<T> {
    
    private CategoryMapper categoryMapper;
    
    public CategoryExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
    
    /**
     每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    private List<CategoryExcelVo> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    
    @Override
    public void invoke(T t, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(t));
        cachedDataList.add((CategoryExcelVo) t);
        if (cachedDataList.size() >= BATCH_COUNT) {
            // 满足100条时,保存一次
            saveData();
            // 释放内存
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }
    
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }
    
    /**
     加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        categoryMapper.batchInsert(cachedDataList);
        log.info("存储数据库成功！");
    }
}
