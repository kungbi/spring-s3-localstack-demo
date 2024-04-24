package com.demo.localstack_demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloud.aws")
@Data
public class AwsProperties {

    private S3 s3;
    private Credentials credentials;
    private String region;

    @Data
    public static class S3 {

        private String bucket;
    }

    @Data
    public static class Credentials {

        private String accessKey;
        private String secretKey;
    }

}
