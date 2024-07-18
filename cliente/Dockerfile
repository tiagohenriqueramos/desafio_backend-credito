# Usar uma imagem base do Java
FROM openjdk:17-jdk-alpine

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR do aplicativo para o diretório de trabalho
COPY target/cliente-0.0.1-SNAPSHOT.jar /app/cliente.jar

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/cliente.jar"]
