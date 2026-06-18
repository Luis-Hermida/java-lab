# Spring JPA API REST — API Specification

## Description

`"spring/3.2-spring-jpa-api-rest/"` exposes a **full CRUD REST API** for `Employee` records backed by **MySQL** and a hand-written **DAO** (`EntityManager`). Same HTTP surface as modules 3.3 and 3.5; persistence here uses `"EmployeeDAOImpl"` instead of `JpaRepository`.

**Base URL:** `http://localhost:8080`  
**API prefix:** `/api`

**Prerequisites:** Java 21, MySQL database `employee_directory`, user `springstudent` (see `"sql-scripts/employee-directory.sql"`).

---

## Controller index

| Controller | Base path | Backend |
|------------|-----------|---------|
| `"EmployeeControler"` | `/api` | `EmployeeService` → `EmployeeDAO` → `EntityManager` |

---

## Employee JSON shape

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

---

## API Endpoints

Source: `"src/main/java/com/springboot/employee/crud/rest/EmployeeControler.java"`

#### GET `/api/employees`

- **Description**: List all employees from the database
- **Method**: GET
- **Response**: JSON array of `Employee`
- **Learn**: Read path through service → DAO → JPQL `from Employee`

#### GET `/api/employees/{employeeId}`

- **Description**: Single employee by primary key
- **Method**: GET
- **Response**: JSON `Employee` or 500 if not found (`RuntimeException`)
- **Learn**: `entityManager.find(Employee.class, id)` in DAO

#### POST `/api/employees`

- **Description**: Create employee; server assigns id
- **Method**: POST
- **Request body**: JSON `Employee` without id (controller sets `id` to `0`)
- **Response**: Saved `Employee` with generated id
- **Learn**: `merge` on new entity triggers INSERT

#### PUT `/api/employees`

- **Description**: Update existing employee (full replace via body)
- **Method**: PUT
- **Request body**: JSON `Employee` including `id`
- **Response**: Updated `Employee`

#### PATCH `/api/employees/{employeeId}`

- **Description**: Partial update using JSON merge
- **Method**: PATCH
- **Request body**: JSON map of fields to change, e.g. `{"firstName":"Jane"}`
- **Response**: Updated `Employee`
- **Details**: `JsonMapper.updateValue` merges patch into loaded entity; `id` in body is rejected
- **Learn**: PATCH vs PUT — partial vs full resource update

#### DELETE `/api/employees/{employeeId}`

- **Description**: Delete by id
- **Method**: DELETE
- **Response**: Plain text — `Deleted employee id - {employeeId}`

---

## Examples

```bash
curl http://localhost:8080/api/employees
curl http://localhost:8080/api/employees/1
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Maria","lastName":"Garcia","email":"maria@example.com"}'
curl -X PATCH http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{"lastName":"Updated"}'
curl -X DELETE http://localhost:8080/api/employees/1
```

---

## Concepts

| Concept | This module |
|---------|-------------|
| REST CRUD | `"EmployeeControler.java"` |
| Service layer | `"EmployeeServiceImpl.java"` |
| Manual JPA DAO | `"EmployeeDAOImpl.java"` |
| `@RequestBody` JSON | POST/PUT/PATCH |
| Partial update | PATCH + `JsonMapper` |
| MySQL + JPA entity | `"Employee.java"`, `"application.properties"` |

Compare with **3.3** (`JpaRepository`) and **3.4** (auto REST at `/api/members`).

---

## Configuration

`"src/main/resources/application.properties"`:

- `jdbc:mysql://localhost:3306/employee_directory`
- Credentials: `springstudent` / `springstudent`

---

## Commands

From `"spring/3.2-spring-jpa-api-rest/"`:

```bash
./mvnw spring-boot:run
./mvnw test
```

---

## References

- Controller: `"src/main/java/com/springboot/employee/crud/rest/EmployeeControler.java"`
- DAO: `"src/main/java/com/springboot/employee/crud/dao/EmployeeDAOImpl.java"`
- Prior module: `"spring/2-spring-boot-hibernate-jpa-crud/api_specification.md"`
- Next: `"spring/3.3-spring-data-jpa/api_specification.md"`

---

## Notes / Caveats

- Class name **`EmployeeControler`** is missing an **r** in Controller.
- Not-found cases throw generic `RuntimeException` (500), not structured 404.
- **`deleteById` in DAO** does not null-check before `remove` — can NPE if id missing.
