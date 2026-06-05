# Spring Boot Hibernate JPA CRUD — Project Map

## Description

`"spring/2-spring-boot-hibernate-jpa-crud/"` is the second Spring module in this learning repo. It teaches **persisting data to MySQL with Spring Data JPA and Hibernate** using a hand-written **DAO layer** on top of the JPA `EntityManager`.

You will see:

- A JPA entity mapped to a relational table (`Student` → `student`)
- A DAO interface + `@Repository` implementation with CRUD and JPQL queries
- Constructor injection of `EntityManager`
- `@Transactional` write operations
- `CommandLineRunner` demos that exercise the DAO on startup (console, not HTTP)
- MySQL setup scripts and datasource configuration
- Hibernate SQL logging and `ddl-auto=create` for local development

**Prerequisites:** Java 21 (see `"pom.xml"`), MySQL running locally, completion of or familiarity with `"spring/1-spring-boot-setup/"` for basic Spring Boot concepts. **No REST layer** in this module.

For DAO method detail and demo operations, see `"api_specification.md"`.

---

## Layout

```text
spring/2-spring-boot-hibernate-jpa-crud/
├── pom.xml                              # Maven build — Spring Boot 4.0.6, Java 21, JPA + MySQL
├── project_specification.md             # This file — structure, learning path, key files
├── api_specification.md                 # StudentDAO contract + CommandLineRunner demos
├── mvnw / mvnw.cmd                      # Maven wrapper
├── sql-scripts/
│   ├── 01-create-user.sql               # Creates MySQL user springstudent
│   └── 02-student-tracker.sql           # Creates database + student table DDL
├── src/main/java/com/lab/crud/demo/
│   ├── Application.java                 # Entry point + CommandLineRunner CRUD demos
│   ├── entity/
│   │   └── Student.java                 # @Entity — maps to student table
│   └── dao/
│       ├── StudentDAO.java              # Persistence contract (save, find, update, delete)
│       └── StudentDAOImpl.java          # EntityManager + JPQL implementation
├── src/main/resources/
│   └── application.properties           # MySQL URL, credentials, Hibernate ddl-auto, SQL logging
└── src/test/java/com/lab/crud/demo/
    └── ApplicationTests.java            # @SpringBootTest smoke test — context loads with DB
```

Tooling folders (`.mvn/wrapper/`) are omitted — not part of the learning story.

---

## Key files

| File | Role | What to notice |
|------|------|----------------|
| `"pom.xml"` | Build & dependencies | Spring Boot **4.0.6**, Java **21**; `spring-boot-starter-data-jpa`, `mysql-connector-j`; **no** web starter |
| `"src/main/java/com/lab/crud/demo/Application.java"` | Entry + demos | `@SpringBootApplication`; `CommandLineRunner` bean calls `StudentDAO` methods |
| `"src/main/java/com/lab/crud/demo/entity/Student.java"` | JPA entity | `@Entity`, `@Table`, `@Id`, `@GeneratedValue(IDENTITY)`; no-arg ctor for Hibernate |
| `"src/main/java/com/lab/crud/demo/dao/StudentDAO.java"` | DAO interface | CRUD + `findByLastName` + `deleteAll` contract |
| `"src/main/java/com/lab/crud/demo/dao/StudentDAOImpl.java"` | DAO implementation | `EntityManager` injection; `persist`/`merge`/`remove`; JPQL queries |
| `"src/main/resources/application.properties"` | DB & JPA config | `student_tracker` datasource; `ddl-auto=create`; Hibernate SQL debug |
| `"sql-scripts/01-create-user.sql"` | DB user setup | `springstudent` user and grants |
| `"sql-scripts/02-student-tracker.sql"` | Schema bootstrap | Manual DDL — Hibernate also creates schema via `ddl-auto` |
| `"api_specification.md"` | Persistence API reference | Every DAO method and startup demo documented |

---

## Learning path

1. **Database setup** — Run `"sql-scripts/01-create-user.sql"` then `"02-student-tracker.sql"` against MySQL.
   - **Learn:** App expects `student_tracker` on `localhost:3306` with user `springstudent`.
   - **Try:** `mysql -u springstudent -pspringstudent -e "USE student_tracker; SHOW TABLES;"`

2. **Build dependencies** — Skim `"pom.xml"`.
   - **Learn:** `spring-boot-starter-data-jpa` pulls in Hibernate + transaction support; MySQL driver is runtime-only.
   - **Notice:** No `spring-boot-starter-web` — this module is persistence-only.

3. **Configuration** — Read `"application.properties"`.
   - **Learn:** Datasource URL/credentials, `ddl-auto=create` (recreates tables each run), SQL logging levels.
   - **Try:** Change `logging.level.org.hibernate.SQL` to `warn` and compare console output.

4. **Entity mapping** — Read `"entity/Student.java"`.
   - **Learn:** How Java fields map to columns; why a no-arg constructor is required; `IDENTITY` id generation.
   - **Try:** Match field names to columns in `"02-student-tracker.sql"`.

5. **DAO contract** — Read `"dao/StudentDAO.java"`.
   - **Learn:** Interface separates persistence API from implementation — same pattern as a future service layer.

6. **DAO implementation** — Read `"dao/StudentDAOImpl.java"` method by method: `save` → `findById` → `findAll` → `findByLastName` → `update` → `delete` → `deleteAll`.
   - **Learn:** `persist` vs `merge`; JPQL entity names vs table names; `@Transactional` on writes.
   - **Try:** Watch SQL in the console when each method runs.

7. **Startup demos** — Read `"Application.java"` — focus on `commandLineRunner` and private helper methods.
   - **Learn:** Only `createMultipleStudents` is active by default; uncomment one demo at a time to practice each operation.
   - **Try:** `./mvnw spring-boot:run` and verify three rows inserted.

8. **API reference** — Read `"api_specification.md"` for full DAO signatures, expected output, and MySQL verify commands.

9. **Tests** — Read `"ApplicationTests.java"`.
   - **Learn:** `@SpringBootTest` loads JPA + datasource — requires MySQL to be up (unlike an in-memory test).

---

## Examples

### Default startup behavior

With `createMultipleStudents` uncommented in `"Application.java"`, running the app inserts three students and prints their generated ids. See **Commands** below.

### Switch demos

In `"Application.java"`, swap the active line inside `commandLineRunner`:

```java
// createMultipleStudents(studentDAO);
queryForAllStudents(studentDAO);
```

Restart to list all rows from the DAO instead of creating new ones.

### JPQL vs table name

In `"StudentDAOImpl.java"`, queries use `FROM Student` (entity class name), not `FROM student` (table name). A common learner mistake is mixing the two.

---

## Commands

Run from `"spring/2-spring-boot-hibernate-jpa-crud/"`.

**Prepare MySQL (first time):**

```bash
mysql -u root -p < "sql-scripts/01-create-user.sql"
mysql -u root -p < "sql-scripts/02-student-tracker.sql"
```

**Windows (PowerShell)** — run from project directory; adjust paths if needed:

```bash
Get-Content "sql-scripts\01-create-user.sql" | mysql -u root -p
Get-Content "sql-scripts\02-student-tracker.sql" | mysql -u root -p
```

**Run and test:**

```bash
./mvnw spring-boot:run
./mvnw test
```

Windows:

```bash
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
```

**Verify data:**

```bash
mysql -u springstudent -pspringstudent -e "USE student_tracker; SELECT * FROM student;"
```

---

## Concepts

| Concept | Where it appears |
|---------|------------------|
| Spring Boot + JPA auto-config | `"pom.xml"`, `"Application.java"` |
| JPA entity mapping | `"Student.java"` |
| `EntityManager` | `"StudentDAOImpl.java"` |
| `@Repository` | `"StudentDAOImpl.java"` |
| Constructor injection | `StudentDAOImpl(EntityManager)` |
| `@Transactional` | Write methods in `"StudentDAOImpl.java"` |
| `persist` / `merge` / `remove` | `save`, `update`, `delete` |
| JPQL queries | `findAll`, `findByLastName`, `deleteAll` |
| Identity key generation | `@GeneratedValue(strategy = IDENTITY)` on `"Student.java"` |
| `ddl-auto=create` | `"application.properties"` — dev-only schema reset |
| `CommandLineRunner` | `"Application.java"` — run code after context starts |
| Datasource configuration | `"application.properties"` + `"sql-scripts/"` |

---

## References

- Persistence API detail: `"api_specification.md"`
- Prior module (DI + REST): `"spring/1-spring-boot-setup/project_specification.md"`
- Next module (REST): `"spring/3-spring-rest/"`
- Project mapping agent: `"agents/map-project.md"`
- Markdown rules: `"agents/documents.md"`
- Repo overview: `"README.md"`
- [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/reference/jpa.html)
- [Hibernate ORM documentation](https://hibernate.org/orm/documentation/)

---

## Notes / Caveats

- **MySQL required:** The app and tests fail without a reachable MySQL instance at the configured URL.
- **`ddl-auto=create`:** Tables are recreated on every startup — data does not persist across runs unless you change this setting.
- **No HTTP API:** REST comes in `"spring/3-spring-rest/"` or by adding controllers on top of `StudentDAO` yourself.
- **Demo id assumptions:** `updateStudent` / `deleteStudent` examples in `"Application.java"` use id `1` — only valid after rows exist.
- **Read module 1 first:** Understanding `@Autowired`, beans, and Spring Boot startup from `"spring/1-spring-boot-setup/"` makes this module easier to follow.
