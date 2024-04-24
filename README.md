# spring-s3-localstack-demo

spring에 s3와 localstack을 적용한 demo project.

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