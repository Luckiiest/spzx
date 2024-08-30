package com.codelucky.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    
    /**
     文件上传
     @param file
     @return
     */
    String fileUpload(MultipartFile file);
}
