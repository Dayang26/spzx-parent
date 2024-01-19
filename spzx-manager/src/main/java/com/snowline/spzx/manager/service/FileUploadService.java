package com.snowline.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Snow
 * @create 2024-01-16 4:31
 */
public interface FileUploadService {

    //头像上传
    String upload(MultipartFile file);
}
