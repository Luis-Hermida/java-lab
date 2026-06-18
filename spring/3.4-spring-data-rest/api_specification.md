# Spring Data REST — API Specification

## Description

`"spring/3.4-spring-data-rest/"` has **no hand-written `@RestController`**. Spring Data REST **auto-exposes** the `"EmployeeRepository"` as a hypermedia API under `/api/members` (custom collection path).

**Base URL:** `http://localhost:8080`  
**API prefix:** `/api` (from `spring.data.rest.base-path`)

**Prerequisites:** Java 21, MySQL `employee_directory`, `"sql-scripts/employee-directory.sql"`.

---

## API surface overview

| Layer | Present? | Source |
|-------|----------|--------|
| Custom `@RestController` | **No** | — |
| Spring Data REST | **Yes** | `"EmployeeRepository.java"` + `@RepositoryRestResource(path = "members")` |

Collection URL uses **`members`**, not `employees`.

---

## Auto-generated endpoints

Repository: `"src/main/java/com/springboot/employee/crud/dao/EmployeeRepository.java"`

| Method | Path | Action |
|--------|------|--------|
| GET | `/api` | API discovery (links to collections) |
| GET | `/api/members` | List employees (paginated, page size 20) |
| GET | `/api/members/{id}` | Single employee |
| POST | `/api/members` | Create employee |
| PUT | `/api/members/{id}` | Replace employee |
| PATCH | `/api/members/{id}` | Partial update (if supported by config) |
| DELETE | `/api/members/{id}` | Delete employee |

Responses are typically **HAL+JSON** with `_links`, `_embedded`, and `page` metadata — not plain arrays like module 3.2/3.3.

**Employee JSON (request/embedded resource):**

```json
{"firstName":"John","lastName":"Doe","email":"john.doe@example.com"}
```

---

## Endpoint details

#### GET `/api`

- **Description**: Root discovery document — links to exposed collections
- **Learn**: Spring Data REST HATEOAS entry point

#### GET `/api/members`

- **Description**: Paginated collection of `Employee` entities
- **Details**: `spring.data.rest.default-page-size=20` in `"application.properties"`
- **Learn**: No controller code required — repository interface drives HTTP

#### GET `/api/members/{id}`

- **Description**: One employee by primary key
- **Response**: HAL document with `firstName`, `lastName`, `email`, and `_links`

#### POST `/api/members`

- **Description**: Creates a new row; server assigns `id`
- **Request**: JSON body without id
- **Learn**: Same entity as modules 3.2/3.3, different URL path and response shape

#### PUT / PATCH / DELETE `/api/members/{id}`

- **Description**: Standard Spring Data REST item resource operations
- **Learn**: Compare manual CRUD in `"spring/3.3-spring-data-jpa/api_specification.md"`

---

## Examples

From `"spring/3.4-spring-data-rest/"`:

```bash
curl http://localhost:8080/api
curl http://localhost:8080/api/members
curl http://localhost:8080/api/members/1
curl -X POST http://localhost:8080/api/members \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Leslie","lastName":"Knope","email":"leslie@example.com"}'
curl -X DELETE http://localhost:8080/api/members/1
```

---

## Concepts

| Concept | Where |
|---------|-------|
| `@RepositoryRestResource` | `"EmployeeRepository.java"` — path `members` |
| `spring.data.rest.base-path` | `"application.properties"` → `/api` |
| Hypermedia / HAL | Response `_links` |
| Zero controller CRUD | Contrast with 3.2 and 3.3 |
| `JpaRepository` | Same interface type as 3.3 |

---

## Configuration

`"src/main/resources/application.properties"`:

| Property | Value |
|----------|-------|
| `spring.datasource.url` | `jdbc:mysql://localhost:3306/employee_directory` |
| `spring.data.rest.base-path` | `/api` |
| `spring.data.rest.default-page-size` | `20` |

Dependencies: `spring-boot-starter-data-rest`, `spring-boot-starter-data-jpa`, MySQL driver.

---

## Commands

```bash
./mvnw spring-boot:run
```

From `"spring/3.4-spring-data-rest/"`.

---

## References

- Repository: `"src/main/java/com/springboot/employee/crud/dao/EmployeeRepository.java"`
- Entity: `"src/main/java/com/springboot/employee/crud/entity/Employee.java"`
- Manual REST: `"spring/3.3-spring-data-jpa/api_specification.md"`
- [Spring Data REST reference](https://docs.spring.io/spring-data/rest/docs/current/reference/html/)

---

## Notes / Caveats

- Paths are **`/api/members`**, not `/api/employees` — easy to confuse with modules 3.2/3.3/3.5.
- Responses include HAL metadata — clients differ from plain JSON CRUD tutorials.
- No Swagger in this module — see `"spring/3.5-spring-data-jpa-swagger/"`.
