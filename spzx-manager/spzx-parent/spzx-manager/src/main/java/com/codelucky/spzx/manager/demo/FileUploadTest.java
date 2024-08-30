package com.codelucky.spzx.manager.demo;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.FileInputStream;

public class FileUploadTest {
    public static void main(String[] args) {
        try {
            // 创建Minio客户端
            MinioClient minioClient = MinioClient.builder()
                                                 .endpoint("http://192.168.116.33:9001")
                                                 .credentials("admin", "admin123456")
                                                 .build();
            
            // 判断是否存在指定的桶
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                                                                     .bucket("spzx-bucket")
                                                                     .build());
            // 如果不存在则创建
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder()
                                                     .bucket("spzx-bucket")
                                                     .build());
            } else {
                System.out.println("Bucket 'spzx-bucket' already exists.");
            }
            
            FileInputStream fis = new FileInputStream("D://01.jpg");
            // 上传
            minioClient.putObject(PutObjectArgs.builder()
                                               .bucket("spzx-bucket")
                                               .object("01.jpg")
                                               .stream(fis, fis.available(), -1)
                                               //.contentType("video/mp4")
                                               .build());
            
            // 路径处理
            String fileUrl = "http://192.168.116.33:9001/spzx-bucket/01.jpg";
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
