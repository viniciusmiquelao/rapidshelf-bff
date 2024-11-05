# Usa a imagem Maven para construir o projeto
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o arquivo pom.xml e instala as dependências no modo offline
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o restante dos arquivos do projeto e compila o artefato
COPY src ./src
RUN mvn clean package dependency:resolve -DskipTests -X

# Usando a imagem OpenJDK para executar a aplicação
FROM eclipse-temurin:21-alpine
WORKDIR /app

# Copia o arquivo .jar gerado para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta padrão do Spring Boot
EXPOSE 3000

# Executa o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
