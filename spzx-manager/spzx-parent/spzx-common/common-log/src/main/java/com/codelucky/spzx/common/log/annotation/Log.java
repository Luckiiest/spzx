package com.codelucky.spzx.common.log.annotation;

import com.codelucky.spzx.common.log.enums.OperatorType;

import java.lang.annotation.*;

/**
 元注解: 修饰注解的注解
 元数据: 修饰数据的数据
 1) @Target: 当前注解可以修饰的位置
 1.1)TYPE: 类
 1.2)METHOD: 方法
 1.3)FIELD: 字段
 1.4)PARAMETER: 参数
 2) @Retention 生命周期
 2.1) java-class --内存
 2.2) SOURCE 源代码
 2.3) CLASS 字节码
 2.4) RUNTIME 运行时
 3) @Inherited 继承
 当前注解如果加在父类上,子类会不会收到影响
 4) @Documentd 文档生成
 JavaDoc */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Log {
    // 模块名称
    public String title();
    
    // 操作人类别
    public OperatorType operatorType() default OperatorType.MANAGE;
    
    // 业务类型(0其他 1新增 2修改 3删除)
    public int businessType();
    
    // 是否保存请求的参数
    public boolean isSaveRequestData() default true;
    
    // 是否保存响应的参数
    public boolean isSaveResponseData() default true;
}
