package com.demo.localstack_demo.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class UploadImageDto {

    @Getter
    @Builder
    public static class UploadImageDtoRequest {

        private MultipartFile image;
    }

    @Getter
    @Builder
    public static class UploadImageDtoResponse {

        private String URL;

    }

}
