package com.restaurant.menuservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class AmazonS3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    public AmazonS3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try {
            //Cấu hình Request đẩy lên S3
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType(file.getContentType())
                    .build();

            //đẩy file lên dạng luồng dữ liệu (InputStream)
            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            // Lấy từ S3 về theo url
            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);

        } catch (IOException e) {
            // Log lỗi ra màn hình Console
            throw new RuntimeException("Lỗi đọc file vật lý: " + e.getMessage());
        } catch (Exception e) {
            // Bắt mọi lỗi từ phía AWS
            throw new RuntimeException("Lỗi từ phía AWS S3: " + e.getMessage());
        }
    }
}