package com.demo.localstack_demo.dto;

import java.util.ArrayList;
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

    @Getter
    public static class GetImageListDtoRequest {

        private Long page;
    }

    @Getter
    public static class GetImageListDtoResponse {

        private ArrayList<String> urlList;
    }

}
