package com.tliasservice.controller;

import com.tliasservice.anno.Log;
import com.tliasservice.pojo.Result;
import com.tliasservice.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @Log
    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        log.info("上传文件：{}",file);
        String imageUrl = fileService.upload(file);
        return Result.success(imageUrl);
    }
}
