# spzx



## 项目介绍
这是一个SpringBoot+SpringCloud项目

1. 数据库：Mysql + Redis
2. 反向代理：Nginx
3. 服务注册和配置中心: Nacos
4. 熔断和限流：Sentinel
5. 网关：Gateway
6. 服务接口调用：OpenFeign

本服务后端管理项目采用了单体架构进行开发，前台系统采用了微服务架构进行开发

## 安装指南
1. 克隆仓库：
    ```bash
    git clone https://github.com/Luckiiest/spzx.git
    ```

2. 进入项目目录：
    ```bash
    cd spzx
    ```

3. 项目目录内容

   ```
   h5前端用户页面		-> 项目前端页面
   spzx-client			-> 项目前台
   spzx-manager		-> 项目管理端
   数据库		
   spzx-compose..yml
   环境配置.md
   ```

## 使用方法

1. 使用idea将前台项目和管理端进行打开, 并打包且到linux中进行部署
2. 前端项目放入nginx中,进行反向代理向后端发出请求