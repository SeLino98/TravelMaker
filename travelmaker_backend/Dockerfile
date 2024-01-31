FROM openjdk:17-jdk-slim

ARG JAR_FILE=build/libs/*.jar
# jar 파일 복제
COPY ${JAR_FILE} app.jar

# sudo 설치 명령어
RUN yum install -y sudo

# 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]