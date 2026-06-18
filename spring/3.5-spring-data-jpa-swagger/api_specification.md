# Spring Data JPA + Swagger — API Specification

## Description

`"spring/3.5-spring-data-jpa-swagger/"` is module **3.3** plus **OpenAPI / Swagger UI** via springdoc. Same hand-written **`/api/employees`** CRUD; interactive docs at custom paths.

**Base URL:** `http://localhost:8080`  
**REST prefix:** `/api`  
**Swagger UI:** `/api/swagger-ui.html`  
**OpenAPI JSON:** `/api/v3/api-docs`

**Prerequisites:** Java 21, MySQL `employee_directory`.

---

## Controller index

| Surface | Path | Source |
|---------|------|--------|
| REST CRUD | `/api/employees` | `"EmployeeControler.java"` |
| Swagger UI | `/api/swagger-ui.html` | springdoc (see `"application.properties"`) |
| API docs JSON | `/api/v3/api-docs` | springdoc |

Persistence: `EmployeeService` → `"EmployeeRepository"` (same as 3.3).

---

## REST endpoints

Source: `"src/main/java/com/springboot/employee/crud/rest/EmployeeControler.java"`

Identical routes to `"spring/3.3-spring-data-jpa/api_specification.md"`:

| Method | Path | Action |
|--------|------|--------|
| GET | `/api/employees` | List all |
| GET | `/api/employees/{employeeId}` | Get by id |
| POST | `/api/employees` | Create |
| PUT | `/api/employees` | Full update |
| PATCH | `/api/employees/{employeeId}` | Partial update |
| DELETE | `/api/employees/{employeeId}` | Delete |

**Employee JSON:**

```json
{"id":1,"firstName":"John","lastName":"Doe","email":"john.doe@example.com"}
```

---

## Swagger / OpenAPI endpoints

#### GET `/api/swagger-ui.html`

- **Description**: Browser UI to explore and try REST endpoints
- **Learn**: springdoc scans `@RestController` and generates OpenAPI from mappings + `@RequestBody` types

#### GET `/api/v3/api-docs`

- **Description**: OpenAPI 3 JSON document backing the UI
- **Learn**: Useful for codegen, Postman import, or CI contract checks

Configured in `"application.properties"`:

```properties
springdoc.swagger-ui.path=/api/swagger-ui.html
springdoc.api-docs.path=/api/v3/api-docs
```

Dependency: `springdoc-openapi-starter-webmvc-ui` **3.0.1** in `"pom.xml"`.

---

## Examples

**Try REST:**

```bash
curl http://localhost:8080/api/employees
```

**Open in browser (app running):**

- Swagger UI: `http://localhost:8080/api/swagger-ui.html`
- Raw OpenAPI: `http://localhost:8080/api/v3/api-docs`

**Create via curl:**

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Tom","lastName":"Hanks","email":"tom@example.com"}'
```

---

## Concepts

| Concept | Where |
|---------|-------|
| Same REST as 3.3 | `"EmployeeControler.java"` |
| `JpaRepository` | `"EmployeeRepository.java"` |
| OpenAPI generation | springdoc + `@RestController` |
| Custom doc paths | `"application.properties"` |

---

## Configuration

MySQL settings match 3.3 plus springdoc paths (see above).

---

## Commands

From `"spring/3.5-spring-data-jpa-swagger/"`:

```bash
./mvnw spring-boot:run
```

Then open Swagger UI in a browser.

---

## References

- Controller: `"src/main/java/com/springboot/employee/crud/rest/EmployeeControler.java"`
- Base CRUD doc: `"spring/3.3-spring-data-jpa/api_specification.md"`
- Auto REST (no controller): `"spring/3.4-spring-data-rest/api_specification.md"`
- [springdoc OpenAPI](https://springdoc.org/)

---

## Notes / Caveats

- **`EmployeeControler`** typo persists.
- Swagger documents the **manual** controller only — not Spring Data REST hypermedia (see 3.4).
- Ensure port 8080 is free before opening Swagger UI.
