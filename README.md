# spring-s3-localstack-demo

spring에 s3와 localstack을 적용한 demo project.

### Project 구조

![spring_s3_localstack](https://github.com/kungbi/spring-s3-localstack-demo/assets/16620948/bde9c1b0-d7d2-48cd-890a-205e4b340347)

### application.yml 설정

보안상 `application.yml`은 gitignore에 추가되어 있습니다. 아래와 같이 설정해주시면 됩니다.

```yml
spring:
  application:
    name: localstack_demo
  servlet:
    multipart:
      max-file-size: 5MB
      max-request-size: 10MB
cloud:
  aws:
    s3:
      bucket: "bucket-name"
    credentials:
      accessKey: "accessKey"
      secretKey: "secretKey"
    region: us-east-2
    stack:
      auto: false
```