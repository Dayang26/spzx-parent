package com.snowline.spzx.manager.controller;

import com.snowline.spzx.manager.service.FileUploadService;
import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Snow
 * @create 2024-01-16 4:30
 */
@RestController
@RequestMapping(value = "/admin/system")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;



    //头像上传
    @PostMapping("/fileUpload")
    public Result fileUpload(@RequestParam("file") MultipartFile file) {
        //获取上传文件
        //调用service方法实现上传，返回minIO里面的路径
        String url = fileUploadService.upload(file);


        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
