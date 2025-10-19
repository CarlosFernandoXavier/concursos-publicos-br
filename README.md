# concursos-publicos-br (Spring Boot, Java 11)

Projeto exemplo utilizando Arquitetura Hexagonal (Ports & Adapters) com dois módulos Maven:

- `application`: regras de negócio (domínio, ports, services)
- `adapter`: camada web (REST) e persistência em memória + bootstrap do Spring Boot

## Pré-requisitos
- Java 11 (JDK 11)
- Maven 3.8+

## Como rodar
Na raiz do projeto:

```bash
# Compilar tudo
mvn clean package

# Subir a aplicação (somente o módulo adapter)
mvn spring-boot:run -pl adapter
```

Por padrão o serviço sobe em `http://localhost:8080`.

## Endpoints principais
Base path: `/api/concursos`

- `GET /api/concursos/concursos-publicos?uf={UF}` — retorna os concursos públicos por UF.
  - Parâmetros:
    - `uf` (query, obrigatório): Sigla do estado, por exemplo `SP`, `RJ`, `MG`.
  - Resposta: `ConcursoPublicoRepresentation` com campos `estado`, `uf`, `concursosAbertos`, `concursosPrevistos`, `mensagem`.

### Exemplos com curl
- Buscar concursos para SP:
```bash
curl "http://localhost:8080/api/concursos/concursos-publicos?uf=SP"
```

## Swagger / OpenAPI
O projeto usa `springdoc-openapi`.

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- OpenAPI YAML: `http://localhost:8080/v3/api-docs.yaml`

## Estrutura do projeto
```
.
├── pom.xml                         # POM pai (empacotamento "pom")
├── application/
│   ├── pom.xml
│   └── src/main/java/com/example/hexagonal/
│       ├── domain/model/Product.java
│       ├── application/port/in/{CreateProductUseCase,GetProductUseCase}.java
│       ├── application/port/out/ProductRepositoryPort.java
│       └── application/service/ProductService.java
└── adapter/
    ├── pom.xml
    └── src/main/java/com/example/hexagonal/
        ├── HexagonalDemoApplication.java
        ├── adapter/in/web/ProductController.java
        ├── adapter/out/persistence/InMemoryProductRepository.java
        └── adapter/config/OpenApiConfig.java
```

## Observações
- Persistência em memória (não há banco); reinícios perdem os dados.
- Para empacotar o jar executável do módulo `adapter`:
```bash
mvn -pl adapter -am clean package
java -jar adapter/target/adapter-0.0.1-SNAPSHOT.jar
```
