package com.codelucky.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.codelucky.spzx.manager.properties.MinioProperties;
import com.codelucky.spzx.manager.service.FileUploadService;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.service.exception.CodeLuckyException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    
    @Resource
    private MinioProperties minioProperties;
    
    /**
     文件上传
     @param file
     @return
     */
    @Override
    
    public String fileUpload(MultipartFile file) {
        String fileUrl = null;
        
        try {
            // MinIO客户端创建
            MinioClient minioClient = MinioClient.builder()
                                                 .endpoint(minioProperties.getEndpointUrl())
                                                 .credentials(minioProperties.getAccessKey(),
                                                              minioProperties.getSecretKey())
                                                 .build();
            
            // 判断桶是否存在
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                                                                     .bucket("spzx-bucket")
                                                                     .build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder()
                                                     .bucket(minioProperties.getBucketName())
                                                     .build());
            } else {
                log.info("Bucket 'asiatrip' already exists.");
            }
            
            // spzx-bucket/20240620/123u10273127361293801.jpg
            
            // 创建文件夹
            String pathDir = DateUtil.format(new Date(), "yyyyMMdd");
            // 随机UUID生成文件名称
            String uuid = UUID.randomUUID()
                              .toString()
                              .replaceAll("-", "");
            
            // 组成文件名称
            String fileName = pathDir + "/" + uuid + file.getOriginalFilename();
            
            // 文件上传
            minioClient.putObject(PutObjectArgs.builder()
                                               .bucket(minioProperties.getBucketName())
                                               .object(fileName)
                                               .stream(file.getInputStream(), file.getSize(), -1)
                                               .contentType(file.getContentType())
                                               .build());
            
            // 拼接上传后的路径
            fileUrl = minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName() + "/" + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CodeLuckyException(ResultCodeEnum.SYSTEM_ERROR);
        }
        return fileUrl;
    }
}
