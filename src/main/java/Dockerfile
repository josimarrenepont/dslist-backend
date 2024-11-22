# Usar imagem base do OpenJDK 21
FROM openjdk:21-jdk-slim

# Definir ARG para o arquivo JAR gerado pelo Maven
ARG JAR_FILE=target/*.jar

# Copiar o arquivo JAR para o contêiner
COPY ${JAR_FILE} app.jar

# Configurar o comando de execução
ENTRYPOINT ["java", "-jar", "/app.jar"]
