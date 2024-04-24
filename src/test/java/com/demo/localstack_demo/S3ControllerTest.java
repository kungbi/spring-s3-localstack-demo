package com.demo.localstack_demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class S3ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void uploadImage() throws Exception {
        // given
        final String fileName = "test_image"; //파일명
        final String contentType = "png"; //파일타입
        final String filePath = "src/test/resources/" + fileName + "." + contentType; //파일경로
        FileInputStream fileInputStream = new FileInputStream(filePath);

        //Mock파일생성
        MockMultipartFile imageMultipartFile = new MockMultipartFile("image", //name
            fileName + "." + contentType, //originalFilename
            contentType, fileInputStream);

        // when
        // then
        String response = mvc.perform(multipart("/s3/image").file(imageMultipartFile))
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        log.info("response = {}", response);
    }

}