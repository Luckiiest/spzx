package com.codelucky.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExcelListener<T> extends AnalysisEventListener<T> {
    
    private List<T> datas = new ArrayList<>();
    
    
    /**
     每读取一行数据,监听回调就会执行一次
     @param t
     @param analysisContext
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        datas.add(t);
    }
    
    /**
     解析完成后执行一次,用于释放资源,或者进行最终读取时的补充
     @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    
    }
}
