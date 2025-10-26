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

### Metadados do OpenAPI via MessageSource
- Os textos de título, descrição, versão, licença e contato são carregados de `messages.properties` por `OpenApiConfig` usando `MessageSource`.
- Arquivo: `adapter/src/main/java/com/concursospublicosbr/config/OpenApiConfig.java`
- Propriedades: `adapter/src/main/resources/messages.properties` (`openapi.*`)

### Geração de código a partir do OpenAPI
- Especificação: `adapter/src/main/resources/openapi.yml`
- Plugin: `openapi-generator-maven-plugin` (executa em `generate-sources` no módulo `adapter`)
- Saída gerada: `adapter/target/generated-sources/openapi/`
- Pacotes gerados: `com.concursospublicosbr.api` (interfaces, ex.: `ConcursosApi`) e `com.concursospublicosbr.api.model` (modelos)

## Autenticação JWT
- **Dependências**: `spring-boot-starter-security` e `io.jsonwebtoken:jjwt-*` adicionadas no módulo `adapter`.
- **Arquivos principais**:
  - `adapter/src/main/java/com/concursospublicosbr/security/SecurityConfig.java`
  - `adapter/src/main/java/com/concursospublicosbr/security/JwtTokenService.java`
  - `adapter/src/main/java/com/concursospublicosbr/security/JwtAuthenticationFilter.java`
  - `adapter/src/main/java/com/concursospublicosbr/in/web/AuthController.java` (gera tokens)
- **Configurações**: `adapter/src/main/resources/application.properties`
  - `jwt.secret` (obrigatório): defina um segredo com pelo menos 32 bytes.
  - `jwt.expiration` (segundos): padrão `3600`.

### Gerar um token
Endpoint público: `POST /auth/token`

Request exemplo:
```bash
curl -X POST http://localhost:8080/auth/token \
  -H 'Content-Type: application/json' \
  -d '{"username": "user", "roles": ["USER"]}'
```

Resposta:
```json
{ "token": "<JWT>" }
```

### Chamar endpoint protegido
Todos os endpoints sob `/api/**` exigem `Authorization: Bearer <JWT>`.

Exemplo:
```bash
TOKEN=$(curl -s -X POST http://localhost:8080/auth/token -H 'Content-Type: application/json' -d '{"username":"user","roles":["USER"]}' | jq -r .token)
curl -H "Authorization: Bearer $TOKEN" "http://localhost:8080/api/concursos/concursos-publicos?uf=sp"
```

Se não quiser usar `jq`:
```bash
TOKEN="<cole o token aqui>"
curl -H "Authorization: Bearer $TOKEN" "http://localhost:8080/api/concursos/concursos-publicos?uf=sp"
```

## Postman (Pre-request e Tests)
- Configure variáveis de ambiente: `baseUrl` (ex.: `http://localhost:8080`), `username` (ex.: `user`), `roles` (ex.: `USER,ADMIN`).
- Na Collection, adicione este Pre-request Script para obter o token automaticamente antes das requisições protegidas:

```javascript
const baseUrl = pm.environment.get('baseUrl') || 'http://localhost:8080';
const username = pm.environment.get('username') || 'user';
const rolesStr = pm.environment.get('roles') || 'USER';
const roles = rolesStr.split(',').map(s => s.trim()).filter(Boolean);

const body = { username, roles };

pm.sendRequest({
  url: `${baseUrl}/auth/token`,
  method: 'POST',
  header: [
    { key: 'accept', value: 'application/json' },
    { key: 'Content-Type', value: 'application/json' }
  ],
  body: { mode: 'raw', raw: JSON.stringify(body) }
}, function (err, res) {
  if (err) { console.error('Erro ao obter token:', err); return; }
  if (!res || res.code !== 200) { console.warn('Resposta inesperada:', res && res.code, res && res.text()); return; }
  const json = res.json();
  if (json && json.token) {
    pm.environment.set('authToken', json.token);
    // Opcional: aplica o header Authorization automaticamente
    pm.request.headers.upsert({ key: 'Authorization', value: `Bearer ${json.token}` });
  }
});
```

- Em cada requisição protegida, adicione o header `Authorization: Bearer {{authToken}}` (ou use a aba Authorization com Bearer Token = `{{authToken}}`).

- Em uma requisição separada de autenticação, você pode usar este Tests para salvar o token manualmente:

```javascript
pm.test('status 200', () => pm.response.to.have.status(200));
const json = pm.response.json();
pm.test('tem token', () => pm.expect(json).to.have.property('token'));
pm.environment.set('authToken', json.token);
```

## Tabela de Endpoints
| Método | Caminho                                   | Autenticação        | Descrição                                   |
|-------:|-------------------------------------------|---------------------|----------------------------------------------|
| POST   | `/auth/token`                              | Pública             | Gera um token JWT                            |
| GET    | `/api/concursos/concursos-publicos?uf=UF` | Bearer `{{authToken}}` | Retorna concursos públicos por UF            |

## Mensagens externalizadas (i18n)
- Mensagens exibidas ao usuário foram movidas para `adapter/src/main/resources/messages.properties`.
- Configuração do Spring para carregar mensagens (em `adapter/src/main/resources/application.properties`):
  - `spring.messages.basename=messages`
  - `spring.messages.encoding=UTF-8`
- Uso no código:
  - `GlobalExceptionHandler` injeta `MessageSource` e consulta chaves como `error.internal`, `error.not_found.uf` e `error.violation.uf_list`.
    Arquivo: `adapter/src/main/java/com/concursospublicosbr/in/web/GlobalExceptionHandler.java`
  - `OpenApiConfig` carrega metadados via chaves `openapi.*` do `messages.properties`.

## Executar com Docker
- Dockerfile multi-stage localizado em `Dockerfile` (raiz do projeto). Contexto filtrado por `.dockerignore`.

### Build da imagem
OBS: executar o comando abaixo na raiz do projeto
```bash
docker build -t concursos-publicos-br:latest .
```

### Executar o container
```bash
docker run --rm -p 8080:8080 concursos-publicos-br:latest
```

### Configurar opções da JVM (opcional)
```bash
docker run --rm -p 8080:8080 -e JAVA_OPTS="-Xms256m -Xmx512m" concursos-publicos-br:latest
```

### Testar
```bash
curl "http://localhost:8080/api/concursos/concursos-publicos?uf=SP"
```

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

