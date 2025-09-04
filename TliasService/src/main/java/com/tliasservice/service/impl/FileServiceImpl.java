package com.tliasservice.service.impl;

import com.tliasservice.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    private static final String UPLOAD_DIR="E:\\桌面\\Tlias\\TliasService\\images\\";
    private static final String BASE_URL = "http://localhost:8080/images/";
    @Override
    public String upload(MultipartFile file) {
        if(!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
                String uniqueFileName = UUID.randomUUID().toString().replace("-", "")+extName;

                File targetFile = new File(UPLOAD_DIR + uniqueFileName);

                if(!targetFile.getParentFile().exists()){
                    targetFile.getParentFile().mkdirs();
                }

                file.transferTo(targetFile);

                return BASE_URL+uniqueFileName;
            } catch (IOException e) {
                log.info("文件保存失败{}", e.getMessage());
                e.printStackTrace();
            }
        }

        return null;
    }
}
