package com.demo.localstack_demo.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.demo.localstack_demo.dto.UploadImageDto.GetImageListDtoRequest;
import com.demo.localstack_demo.dto.UploadImageDto.GetImageListDtoResponse;
import com.demo.localstack_demo.dto.UploadImageDto.UploadImageDtoRequest;
import com.demo.localstack_demo.dto.UploadImageDto.UploadImageDtoResponse;
import com.demo.localstack_demo.properties.AwsProperties;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;
    private final AwsProperties awsProperties;

    // 랜덤파일명 생성 (파일명 중복 방지)
    private String generateRandomFilename(MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = validateFileExtension(originalFilename);
        String randomFilename = UUID.randomUUID() + "." + fileExtension;
        return randomFilename;
    }

    // 파일 확장자 체크
    private String validateFileExtension(String originalFilename) throws Exception {
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1)
            .toLowerCase();
        List<String> allowedExtensions = Arrays.asList("jpg", "png", "gif", "jpeg");

        if (!allowedExtensions.contains(fileExtension)) {
//            throw new Exception400("file", ErrorMessage.NOT_IMAGE_EXTENSION);
            throw new Exception("NOT_IMAGE_EXTENSION");
        }
        return fileExtension;
    }


    public ResponseEntity<UploadImageDtoResponse> uploadImage(UploadImageDtoRequest request) {
        try {
            log.info(request.getImage().getName());

            String randomFilename = generateRandomFilename(request.getImage());
            log.info("randomFilename: {}", randomFilename);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(request.getImage().getSize());
            metadata.setContentType(request.getImage().getContentType());

            amazonS3.putObject(awsProperties.getS3().getBucket(), randomFilename,
                request.getImage().getInputStream(),
                metadata);
            log.info("upload success");

            return ResponseEntity.ok(UploadImageDtoResponse.builder()
                .URL(amazonS3.getUrl(awsProperties.getS3().getBucket(), randomFilename).toString())
                .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public ResponseEntity<GetImageListDtoResponse> getImageList(GetImageListDtoRequest request) {
        return null;
    }

}
