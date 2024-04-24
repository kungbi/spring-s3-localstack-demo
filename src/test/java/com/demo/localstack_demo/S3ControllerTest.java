package com.demo.localstack_demo;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class S3ControllerTest {

    private final MockMvc mvc;

    @Test
    void uploadImage() {

    }

    @Test
    void getImageList() {
    }
}