package com.demo.localstack_demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.demo.localstack_demo.properties.AwsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.localstack.LocalStackContainer;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class S3DevConfig {

    private final AwsProperties awsProperties;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public LocalStackContainer localStackContainer() {
        return new LocalStackContainer(org.testcontainers.utility.DockerImageName.parse(
            "localstack/localstack:latest")).withServices(LocalStackContainer.Service.S3);
    }

    @Bean
    public AmazonS3 amazonS3(LocalStackContainer localStackContainer) {
        AWSCredentials credentials = new BasicAWSCredentials(localStackContainer.getAccessKey(),
            localStackContainer.getSecretKey());
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withEndpointConfiguration(
                new EndpointConfiguration(localStackContainer.getEndpoint().toString(), "us-east-1"))
            .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        amazonS3.createBucket(awsProperties.getS3().getBucket());
        return amazonS3;
    }
}