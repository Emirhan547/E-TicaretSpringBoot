# Base image olarak OpenJDK 17 kullanıyoruz
FROM openjdk:17-jdk-slim

# Çalışma dizini oluşturuyoruz
WORKDIR /app

# Uygulamanın JAR dosyasını çalışma dizinine kopyalıyoruz
COPY target/e-ticaret-0.0.1-SNAPSHOT.jar app.jar


# Uygulamayı çalıştırıyoruz
ENTRYPOINT ["java", "-jar", "app.jar"]
