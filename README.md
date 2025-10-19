# concursos-publicos-br (Spring Boot, Java 11)

Projeto utilizando Arquitetura Hexagonal (Ports & Adapters) com dois módulos Maven:

- `application`: regras de negócio (domínio, ports, services)
- `adapter`: camada web (REST) + bootstrap do Spring Boot

## Pré-requisitos
- Java 11 (JDK 11)
- Maven 3.8+

## Como rodar
Na raiz do projeto:

```bash
# Compilar tudo
mvn clean package

# Subir a aplicação (somente o módulo adapter)
mvn -pl adapter spring-boot:run
```

Por padrão o serviço sobe em `http://localhost:8080`.

## Endpoints principais
Base path: `/api/concursos`

- `GET /api/concursos/concursos-publicos?uf={UF}` — retorna os concursos públicos por UF.
  - Parâmetros:
    - `uf` (query, obrigatório): Sigla do estado, por exemplo `SP`, `RJ`, `MG`.
  - Resposta: `com.concursospublicosbr.api.model.ConcursoPublicoRepresentation` com campos `estado`, `uf`, `concursosAbertos`, `concursosPrevistos`.

### Exemplos com curl
- Buscar concursos para SP:
```bash
curl "http://localhost:8080/api/concursos/concursos-publicos?uf=SP"
```

## Swagger / OpenAPI
O projeto usa `springdoc-openapi` e gera interfaces a partir de `openapi.yml`.

- Swagger UI: `http://localhost:8080/swagger-ui/index.html` (ou `/swagger-ui.html`)
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- OpenAPI YAML: `http://localhost:8080/v3/api-docs.yaml`

### Geração de código a partir do OpenAPI
- Especificação: `adapter/src/main/resources/openapi.yml`
- Plugin: `openapi-generator-maven-plugin` (executa em `generate-sources` no módulo `adapter`)
- Saída gerada: `adapter/target/generated-sources/openapi/`
- Pacotes gerados: `com.concursospublicosbr.api` (interfaces, ex.: `ConcursosApi`) e `com.concursospublicosbr.api.model` (modelos)

## Estrutura do projeto
```
.
├── pom.xml                         # POM pai (empacotamento "pom")
├── application/
│   ├── pom.xml
│   └── src/main/java/com/concursospublicosbr/
│       ├── domain/model/ConcursoPublico.java
│       ├── port/in/ConcursosPublicosServicePort.java
│       └── (demais classes de domínio/serviços)
└── adapter/
    ├── pom.xml
    ├── src/main/java/com/concursospublicosbr/
    │   ├── HexagonalDemoApplication.java         # ponto de entrada Spring Boot
    │   └── in/web/ConcursosProxyController.java  # implementação de ConcursosApi
    └── src/main/resources/openapi.yml            # especificação OpenAPI
```

## Observações
- Para empacotar o jar executável do módulo `adapter`:
```bash
mvn -pl adapter -am clean package
java -jar adapter/target/adapter-0.0.1-SNAPSHOT.jar
```
- O gerador usa `org.openapitools:jackson-databind-nullable` para tipos `JsonNullable` (já declarado em `adapter/pom.xml`).
- Diretórios de build ignorados no Git: `adapter/target/` e `application/target/`.
