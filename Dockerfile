# DOCKER RUN : 기존의 작업 내역에 변경이 없으면 캐싱된 내역으로 대체
# 멀티 스테이지 1 : 빌드 스테이지
# GraalVm을 기반 이미지로 사용
FROM ghcr.io/graalvm/graalvm-community:17 AS builder

# 작업 디렉토리 설정
WORKDIR /app

# Gradle 래퍼 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Gradle 래퍼에 실행 권한 부여
RUN chmod +x ./gradlew

# 의존성 설치
# 이 단계에서 변경사항이 없다면, 다음 빌드에서 캐시
# gradle 빌드 과정에서 메모리 문제 및 기타 충돌 해결 위해 --no-daemon 활성화
RUN ./gradlew dependencies --no-daemon
# 소스 코드 복사
COPY src src

# 애플리케이션 빌드
RUN ./gradlew clean build --no-daemon

# 멀티 스테이지 2 : 실행 스테이지
FROM ghcr.io/graalvm/graalvm-community:17

# 작업 디렉토리 설정
WORKDIR /app

# 첫번째 빌드 스테이지에서 빌드된 Jar 파일만을 복사
COPY --from=builder /app/build/libs/*.jar app.jar

#실행할 JAR 파일 지정
#여러 파일이 있는 경우 실행할 특정 파일을 지정
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app/app.jar"]