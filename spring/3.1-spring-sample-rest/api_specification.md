# Spring Sample REST — API Specification

## Description

`"spring/3.1-spring-sample-rest/"` introduces **Spring MVC REST** with in-memory data (no database). Controllers return JSON; a global `@ControllerAdvice` maps exceptions to structured error responses.

**Base URL:** `http://localhost:8080`

**Prerequisites:** Java 21, `./mvnw spring-boot:run` from `"spring/3.1-spring-sample-rest/"`. No database.

For folder layout, see `"project_specification.md"` when present.

---

## Controller index

| Controller | Base path | Teaches |
|------------|-----------|---------|
| `"DemoRestController"` | `/test` | Minimal `@RestController` |
| `"StudentRestController"` | `/api` | JSON list, `@PathVariable`, `@PostConstruct` seed data |
| `"StudentRestExceptionHandler"` | (global) | `@ControllerAdvice` error handling |

---

## API Endpoints

### 1. DemoRestController — Hello endpoint

Source: `"src/main/java/com/sample/rest/java/rest/DemoRestController.java"`

#### GET `/test/hello`

- **Description**: Simple sanity-check endpoint
- **Method**: GET
- **Request**: None
- **Response**: Plain text — `Hello world`
- **Learn**: `@RequestMapping` on class + `@GetMapping` on method builds the full path

---

### 2. StudentRestController — In-memory student API

Source: `"src/main/java/com/sample/rest/java/rest/StudentRestController.java"`

#### GET `/api/students`

- **Description**: Returns all students loaded at startup
- **Method**: GET
- **Request**: None
- **Response**: JSON array, e.g. `[{"firstName":"Luiss","lastName":"Hormiga"}, …]`
- **Details**: Data seeded in `@PostConstruct loadData()` — three hard-coded students
- **Learn**: `@RestController` serializes `List<Student>` to JSON automatically

#### GET `/api/students/{studentId}`

- **Description**: Returns one student by **list index** (not a database id)
- **Method**: GET
- **Request**: Path variable `studentId` (int) — valid values `0`, `1`, `2` with default seed data
- **Response**: JSON object — `{"firstName":"…","lastName":"…"}`
- **Errors**: Invalid index throws `StudentNotFoundException` → 404 via `"StudentRestExceptionHandler"`
- **Learn**: `@PathVariable` binds URL segment; this demo uses array index as id — a common learner pitfall

---

### 3. StudentRestExceptionHandler — Global errors

Source: `"src/main/java/com/sample/rest/java/rest/StudentRestExceptionHandler.java"`

Not an HTTP route — handles exceptions from any controller.

| Exception | HTTP status | Response body |
|-----------|-------------|-----------------|
| `StudentNotFoundException` | 404 | `StudentErrorResponse` — status, message, timeStamp |
| Any other `Exception` | 400 | Same shape with BAD_REQUEST status |

- **Learn**: `@ControllerAdvice` centralizes error responses instead of `@ExceptionHandler` on each controller (see commented code in `"StudentRestController.java"`)

---

## Examples

From `"spring/3.1-spring-sample-rest/"` with app running:

```bash
curl http://localhost:8080/test/hello
curl http://localhost:8080/api/students
curl http://localhost:8080/api/students/0
curl http://localhost:8080/api/students/99
```

Expected 404 for invalid id:

```json
{"status":404,"message":"Student id not found - 99","timeStamp":1710000000000}
```

---

## Concepts

| Concept | Where |
|---------|-------|
| `@RestController` / `@GetMapping` | Controllers |
| JSON serialization | Return types `List<Student>`, `Student` |
| `@PathVariable` | `GET /api/students/{studentId}` |
| `@PostConstruct` | Seed in-memory list at startup |
| `@ControllerAdvice` + `@ExceptionHandler` | `"StudentRestExceptionHandler.java"` |
| Custom exception | `"StudentNotFoundException.java"` |

---

## Configuration

`"src/main/resources/application.properties"` — only `spring.application.name=java`. Default port **8080**, no security starter.

---

## Commands

```bash
./mvnw spring-boot:run
./mvnw test
```

Windows: `.\mvnw.cmd spring-boot:run`

---

## References

- Controllers: `"src/main/java/com/sample/rest/java/rest/"`
- Entity POJO: `"src/main/java/com/sample/rest/java/entity/Student.java"`
- Next module (JPA + REST): `"spring/3.2-spring-jpa-api-rest/"`
- API agent: `"agents/api-deep-dive.md"`

---

## Notes / Caveats

- **`studentId` is a list index**, not a persistent primary key.
- No POST/PUT/DELETE on students in this module — read-only API.
- Commented `@ExceptionHandler` blocks in `"StudentRestController.java"` show the per-controller alternative to `@ControllerAdvice`.
