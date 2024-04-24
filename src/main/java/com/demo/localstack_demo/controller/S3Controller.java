package com.demo.localstack_demo.controller;

import com.demo.localstack_demo.dto.UploadImageDto.GetImageListDtoRequest;
import com.demo.localstack_demo.dto.UploadImageDto.GetImageListDtoResponse;
import com.demo.localstack_demo.dto.UploadImageDto.UploadImageDtoRequest;
import com.demo.localstack_demo.dto.UploadImageDto.UploadImageDtoResponse;
import com.demo.localstack_demo.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("/image")
    public ResponseEntity<UploadImageDtoResponse> uploadImage(
        @ModelAttribute UploadImageDtoRequest request) {
        return this.s3Service.uploadImage(request);
    }

    @GetMapping("/images")
    public ResponseEntity<GetImageListDtoResponse> getImageList(GetImageListDtoRequest request) {
        return this.s3Service.getImageList(request);
    }

}
