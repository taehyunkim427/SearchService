# 베이스 이미지로 amazon corretto OpenJDK 17을 사용
FROM amazoncorretto:17 as build

# 작업 디렉토리를 설정
WORKDIR /app

# 프로젝트의 모든 파일을 작업 디렉토리에 복사
COPY . .

ARG REDIS_HOST
ARG REDIS_PORT

ENV REDIS_HOST=${REDIS_HOST}
ENV REDIS_PORT=${REDIS_PORT}

# Gradle을 사용하여 프로젝트를 빌드
RUN ./gradlew clean build

# 빌드한 파일을 실행할 새 이미지를 생성
FROM amazoncorretto:17

# 작업 디렉토리를 설정
WORKDIR /app

# 빌드 이미지에서 생성된 JAR 파일을 현재 이미지로 복사
COPY --from=build /app/build/libs/*.jar /app/app.jar

# 컨테이너가 시작되면 실행할 명령을 설정
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]

# 컨테이너가 수신할 포트를 지정
EXPOSE 8080