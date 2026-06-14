package com.restaurant.menuservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class AmazonS3Service {

    // Thằng S3Client này sẽ được Spring tự động inject từ file AwsS3Config.java của mày vào đây
    private final S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    public AmazonS3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) {
        // Tạo tên file độc nhất bằng cách cộng thêm timestamp để không bị ghi đè ảnh trùng tên
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .acl("public-read") // Cấu hình quyền để ai cũng có thể đọc link ảnh này qua Internet
                    .contentType(file.getContentType()) // Định dạng file (image/jpeg, image/png...)
                    .build();

            // Thực hiện đẩy luồng dữ liệu (InputStream) của file lên S3
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            // Upload thành công thì trả về URL tuyệt đối của ảnh trên AWS S3
            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);

        } catch (IOException e) {
            throw new RuntimeException("Lỗi xảy ra trong quá trình upload file lên AWS S3: " + e.getMessage());
        }
    }
}