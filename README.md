# 📚 Biblioteca API

API REST para gerenciamento de uma biblioteca, desenvolvida com **Spring Boot**. Conta com autenticação via **JWT**, persistência em **PostgreSQL** com migrations versionadas pelo **Flyway** e documentação interativa com **Swagger (OpenAPI)**.

## 🚀 Tecnologias

| Categoria | Tecnologia |
|---|---|
| Linguagem | Java 25 |
| Framework | Spring Boot 4.1.0 |
| Web | Spring Web MVC |
| Persistência | Spring Data JPA + PostgreSQL |
| Migrations | Flyway |
| Segurança | Spring Security + JWT (jjwt 0.12.6) |
| Validação | Spring Validation |
| Cache | Spring Cache |
| Monitoramento | Spring Boot Actuator |
| Documentação | springdoc-openapi (Swagger UI) |
| Utilitários | Lombok |
| Build | Maven (Maven Wrapper incluso) |

## ✅ Pré-requisitos

- [JDK 25](https://jdk.java.net/25/)
- [PostgreSQL](https://www.postgresql.org/download/) (ou Docker)
- Git

> Não é necessário instalar o Maven: o projeto já inclui o Maven Wrapper (`mvnw`).

## ⚙️ Configuração

1. Clone o repositório:

```bash
git clone https://github.com/LucasHenrique-Silva/biblioteca-api.git
cd biblioteca-api
```

2. Crie o banco de dados no PostgreSQL:

```sql
CREATE DATABASE biblioteca;
```

Ou suba um PostgreSQL rapidamente com Docker:

```bash
docker run --name biblioteca-db \
  -e POSTGRES_DB=biblioteca \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 -d postgres
```

3. Configure as variáveis da aplicação em `src/main/resources/application.properties` (ajuste os valores ao seu ambiente):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
spring.datasource.username=postgres
spring.datasource.password=postgres

# Chave secreta usada para assinar os tokens JWT
# (use uma chave longa e aleatória; nunca versione a de produção)
api.security.token.secret=troque-por-uma-chave-secreta
```

> ⚠️ Os nomes das propriedades de JWT podem variar conforme a implementação do projeto. Confira o `application.properties` do repositório.

As tabelas são criadas automaticamente pelo Flyway na primeira execução.

## ▶️ Executando

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

### Gerar o .jar

```bash
./mvnw clean package
java -jar target/biblioteca-api-0.0.1-SNAPSHOT.jar
```

## 📖 Documentação da API

Com a aplicação em execução, acesse o Swagger UI:

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI (JSON):** http://localhost:8080/v3/api-docs

Para acessar endpoints protegidos, faça login, copie o token JWT retornado e clique em **Authorize** no Swagger UI (ou envie o header `Authorization: Bearer <seu_token>`).

## ❤️ Health check

O Actuator expõe o estado da aplicação em:

```
GET http://localhost:8080/actuator/health
```

## 🧪 Testes

```bash
./mvnw test
```

## 📁 Estrutura do projeto

```
biblioteca-api
├── .mvn/wrapper      # Maven Wrapper
├── src
│   ├── main
│   │   ├── java      # Código-fonte da aplicação
│   │   └── resources # Configurações e migrations (Flyway)
│   └── test          # Testes automatizados
├── mvnw / mvnw.cmd   # Scripts do Maven Wrapper
└── pom.xml           # Dependências e build
```

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature: `git checkout -b feature/minha-feature`
3. Commit suas alterações: `git commit -m "feat: minha feature"`
4. Envie para a branch: `git push origin feature/minha-feature`
5. Abra um Pull Request

## 👤 Autor

**Lucas Henrique Silva**
GitHub: [@LucasHenrique-Silva](https://github.com/LucasHenrique-Silva)

## 📄 Licença

Este projeto ainda não possui uma licença definida. Adicione um arquivo `LICENSE` (por exemplo, MIT) caso deseje torná-lo open source.
