FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=product-manager-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
EXPOSE 80/tcp
EXPOSE 80/udp
ENTRYPOINT ["java", "-jar", "application.jar"]