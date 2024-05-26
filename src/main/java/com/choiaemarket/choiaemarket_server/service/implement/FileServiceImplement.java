package com.choiaemarket.choiaemarket_server.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.choiaemarket.choiaemarket_server.service.FileService;

@Service
public class FileServiceImplement implements FileService {

    @Value("${file.path}")
    private String filePath;
    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) return null; // 빈 파일이면 null 반환

        String originalFileName = file.getOriginalFilename(); // 원래 이름 가져오기
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 확장자
        String uuid = UUID.randomUUID().toString(); // 새로운 파일명 부여
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;   // 저장 위치

        try {
            file.transferTo(new File(savePath));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        String url = fileUrl + saveFileName;
        return url;
    }

    @Override
    public Resource getImage(String fileName) {

        Resource resource = null;

        try {
            resource = new UrlResource("file:" + filePath + fileName);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return resource;

    }
    
}
