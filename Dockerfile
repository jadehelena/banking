#gradle
FROM gradle:6.7.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --scan

#spring-boot
FROM openjdk:8-jdk-alpine
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-docker.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-docker.jar"]
