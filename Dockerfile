# 빌드 단계
FROM eclipse-temurin:17-jre-alpine AS builder
LABEL stage=builder

WORKDIR /application

# JAR 파일 이름을 변수로 설정
ARG JAR_FILE=build/libs/recruit-0.0.1-SNAPSHOT.jar

# 애플리케이션 JAR 파일 복사 및 레이어 추출
COPY ./${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# 실행 단계
FROM eclipse-temurin:17-jre-alpine
LABEL maintainer="Juwon Lee <justuser0129@gmail.com>"

WORKDIR /application

# 시간대 설정을 ENV로 이동
ENV TZ=Asia/Seoul

# 레이어 파일 복사
COPY --from=builder /application/dependencies/ ./
COPY --from=builder /application/spring-boot-loader/ ./
COPY --from=builder /application/snapshot-dependencies/ ./
COPY --from=builder /application/application/ ./

# 실행 명령어
ENTRYPOINT ["java", "-Duser.timezone=${TZ}", "org.springframework.boot.loader.launch.JarLauncher"]
