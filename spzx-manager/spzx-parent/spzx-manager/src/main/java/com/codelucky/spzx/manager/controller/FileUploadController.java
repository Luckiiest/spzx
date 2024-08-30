package com.codelucky.spzx.manager.controller;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.FileUploadService;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/system")
@Tag(name = "文件上传")
public class FileUploadController {
    
    @Resource
    private FileUploadService fileUploadService;
    
    /**
     文件上传
     @param file
     @return
     */
    @PostMapping("/fileUpload")
    @Log(title = "文件上传模块-文件上传",
         businessType = 1)
    public Result<String> fileUpload(MultipartFile file) {
        String fileUrl = fileUploadService.fileUpload(file);
        return Result.build(fileUrl, ResultCodeEnum.SUCCESS);
    }
}
