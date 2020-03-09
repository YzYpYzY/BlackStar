# #
# # Build stage
# #
# FROM maven:3.5.4-jdk-8-alpine AS build  
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean package

# #
# # Package stage
# #
# FROM gcr.io/distroless/java  
# COPY --from=build /home/app/target/BlackStar-0.0.1-SNAPSHOT.jar /usr/local/lib/BlackStar.jar
# EXPOSE 4200
# ENTRYPOINT ["java","-jar","/usr/local/lib/BlackStar.jar"]

FROM adminer
EXPOSE 8080