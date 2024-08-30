package com.codelucky.spzx.manager.demo;

import com.alibaba.excel.EasyExcel;
import com.codelucky.spzx.manager.listener.ExcelListener;
import com.codelucky.spzx.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {
    public static void main(String[] args) {
        
        //readExccelDemo();
        writerExcelDemo();
        
    }
    
    private static void writerExcelDemo() {
        List<CategoryExcelVo> list = new ArrayList<>();
        list.add(new CategoryExcelVo(1L, "数码办公", "", 0L, 1, 1));
        list.add(new CategoryExcelVo(11L, "华为手机", "", 1L, 1, 2));
        //    写出实现
        EasyExcel.write("D:\\LONG\\Desktop\\工作簿2.xlsx", CategoryExcelVo.class)
                 .sheet("分类管理")
                 .doWrite(list);
        System.out.println("写出完毕");
    }
    
    private static void readExccelDemo() {
        //    定义文件位置
        String filename = "D:\\LONG\\Desktop\\工作簿1.xlsx";
        // 创建监听器
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>();
        //    读取
        EasyExcel.read(filename, CategoryExcelVo.class, excelListener)
                 .sheet()
                 .doRead();
        //    获取数据
        List<CategoryExcelVo> datas = excelListener.getDatas();
        for (CategoryExcelVo categoryExcelVo : datas) {
            System.out.println(categoryExcelVo);
        }
    }
}
