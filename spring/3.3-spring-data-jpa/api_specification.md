# Spring Data JPA REST — API Specification

## Description

`"spring/3.3-spring-data-jpa/"` exposes the **same `/api/employees` CRUD** as module 3.2, but persistence goes through **`JpaRepository`** (`"EmployeeRepository"`) instead of a manual DAO.

**Base URL:** `http://localhost:8080`  
**API prefix:** `/api`

**Prerequisites:** Java 21, MySQL `employee_directory` (see `"sql-scripts/employee-directory.sql"`).

---

## Controller index

| Controller | Base path | Backend |
|------------|-----------|---------|
| `"EmployeeControler"` | `/api` | `EmployeeService` → `EmployeeRepository` (Spring Data JPA) |

HTTP routes are **identical** to `"spring/3.2-spring-jpa-api-rest/api_specification.md"`. Below is a summary; see that doc for full curl examples.

---

## API Endpoints

Source: `"src/main/java/com/springboot/employee/crud/rest/EmployeeControler.java"`

| Method | Path | Action |
|--------|------|--------|
| GET | `/api/employees` | List all |
| GET | `/api/employees/{employeeId}` | Get by id |
| POST | `/api/employees` | Create (`id` forced to 0) |
| PUT | `/api/employees` | Full update |
| PATCH | `/api/employees/{employeeId}` | Partial update via `JsonMapper` |
| DELETE | `/api/employees/{employeeId}` | Delete |

**Employee JSON:**

```json
{"id":1,"firstName":"John","lastName":"Doe","email":"john.doe@example.com"}
```

---

## What differs from 3.2 (not the HTTP contract)

| Layer | Module 3.2 | Module 3.3 (this) |
|-------|--------------|-------------------|
| Persistence | `"EmployeeDAOImpl"` + `EntityManager` | `"EmployeeRepository extends JpaRepository"` |
| `findById` | Returns `null` from DAO | Service throws if `Optional` empty |
| Boilerplate | Hand-written JPQL | Spring Data generates queries |

- **Learn**: Same REST controller can sit on different persistence implementations

---

## Examples

```bash
curl http://localhost:8080/api/employees
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Amy","lastName":"Lee","email":"amy@example.com"}'
```

---

## Concepts

| Concept | Where |
|---------|-------|
| `JpaRepository` | `"EmployeeRepository.java"` |
| Service delegates to repository | `"EmployeeServiceImpl.java"` |
| REST CRUD + PATCH | `"EmployeeControler.java"` |
| `@Transactional` on writes | Service layer |

---

## Configuration

`"src/main/resources/application.properties"` — MySQL `employee_directory`, `springstudent` credentials.

---

## Commands

From `"spring/3.3-spring-data-jpa/"`:

```bash
./mvnw spring-boot:run
```

---

## References

- Repository: `"src/main/java/com/springboot/employee/crud/dao/EmployeeRepository.java"`
- Manual DAO version: `"spring/3.2-spring-jpa-api-rest/api_specification.md"`
- Auto-generated REST: `"spring/3.4-spring-data-rest/api_specification.md"`
- Swagger variant: `"spring/3.5-spring-data-jpa-swagger/api_specification.md"`

---

## Notes / Caveats

- **`EmployeeControler`** typo (missing **r**).
- Controller null-check on `findById` is redundant — service already throws when missing.
