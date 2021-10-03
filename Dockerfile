FROM openjdk:11 as builder
WORKDIR /app
COPY . /app
RUN ./mvnw clean package


FROM openjdk:11
RUN addgroup --system springgroup && adduser --system --ingroup springgroup springuser
USER springuser:springgroup
COPY --from=builder /app/target/*.jar /emailMicroService.jar
CMD ["java", "-jar", "/emailMicroService.jar"]

