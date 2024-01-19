package com.snowline.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.snowline.spzx.common.exception.SnowLineException;
import com.snowline.spzx.manager.properties.MinioProperties;
import com.snowline.spzx.manager.service.FileUploadService;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import io.minio.*;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * @author Snow
 * @create 2024-01-16 4:31
 */

@Service
public class FileUploadServiceImpl implements FileUploadService {


    @Autowired
    private MinioProperties minioProperties;

    //头像上传
    @Override
    public String upload(MultipartFile file) {
        String url = null;
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient = MinioClient.builder().endpoint(minioProperties.getEndpointUrl()).credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey()).build();

            // Make 'spzx' bucket if not exist.
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found) {
                // Make a new bucket called 'spzx-bucket'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {
                System.out.println("Bucket" + minioProperties.getBucketName() + " already exists.");
            }


            //get uploading file name
            // let fileName to be only with up UUID
            // 根据当前日期对文件进行分组    /20011226/uuidFileName.jpg
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String filename = dateDir + "/" + uuid + file.getOriginalFilename();

            //get inputStream
            InputStream inputStream = file.getInputStream();

            //get fileSize
            long fileSize = file.getSize();

            //stream file upload
            minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucketName()).object(filename).stream(inputStream, fileSize, -1).build());

            //get urlPath of file in MinIO
            //  http://192.168.10.18:9001/spzx-bucket/wallhaven-vq5gr3.jpg
            url = minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName() + "/" + filename;


        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new SnowLineException(ResultCodeEnum.SYSTEM_ERROR);
        }

        return url;
    }
}
