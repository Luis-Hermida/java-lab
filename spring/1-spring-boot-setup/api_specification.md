# Spring Boot Setup — API Specification

## Description

This document describes every REST endpoint in `"spring/1-spring-boot-setup/"` — what each route returns, how dependencies are wired, and what Spring concept it demonstrates.

**Base URL:** `http://localhost:8080` (default Spring Boot port)

**Prerequisites:** App running via `./mvnw spring-boot:run` from `"spring/1-spring-boot-setup/"`. Spring Security is enabled — see **Notes / Caveats**.

For folder layout and suggested read order, see `"project_specification.md"`.

---

## API Endpoints

### 1. HelloWorldController — Basic REST Endpoints

Source: `"src/main/java/com/spring/demo/main/rest/HelloWorldController.java"`

#### GET `/`

- **Description**: Returns a simple `"Lombok is working!!!!"` message via a DTO
- **Method**: GET
- **Auth**: Spring Security enabled — use credentials from `"application.properties"`
- **Request**: None
- **Response**: Plain text — `Lombok is working!!!!`
- **Dependency injection**: None on services; uses `@Value` for properties on other routes
- **Details**: Instantiates `"SampleLombokDTO.java"` with Lombok `@Data` getters/setters
- **Learn**: `@RestController` + minimal DTO without hand-written boilerplate

#### GET `/custom`

- **Description**: Returns custom pet names from application properties
- **Method**: GET
- **Auth**: Spring Security enabled
- **Request**: None
- **Response**: Plain text — `Dog name: Owlyn Cat name: Ojitos` (values from `"application.properties"`)
- **Dependency injection**: Property-based via `@Value("${dog.name}")` and `@Value("${cat.name}")`
- **Details**: Loads `dog.name` and `cat.name` from `"src/main/resources/application.properties"`
- **Learn**: Externalized configuration injected directly into a controller

---

### 2. CoachFieldController — Field Injection Example

Source: `"src/main/java/com/spring/demo/main/controllers/CoachFieldController.java"`

#### GET `/coachField`

- **Description**: Returns daily workout from Cricket Coach using field injection
- **Method**: GET
- **Auth**: Spring Security enabled
- **Request**: None
- **Response**: Plain text — `Practice cricket for 3 >:( hours`
- **Dependency injection**: **Field injection** (not recommended) — `@Autowired` + `@Qualifier("cricketCoach")` on a private field
- **Details**: Demonstrates `"CricketCoach.java"` bean
- **Learn**: Field injection works but hides dependencies — prefer constructor injection
- **Note**: Field injection is discouraged in modern Spring applications

---

### 3. CoachSetterController — Setter Injection Example

Source: `"src/main/java/com/spring/demo/main/controllers/CoachSetterController.java"`

#### GET `/coachSetter`

- **Description**: Returns daily workout from Basketball Coach using setter injection
- **Method**: GET
- **Auth**: Spring Security enabled
- **Request**: None
- **Response**: Plain text — basketball workout string from `"BasketBallCoach.java"`
- **Dependency injection**: **Setter injection** — `@Autowired` on `setCoachLaLaLaLa()` with `@Qualifier("basketBallCoach")`
- **Details**: Setter name is intentionally unconventional to show any method can be the injection point
- **Learn**: Setter injection is more flexible than field injection but still less explicit than constructor injection
- **Note**: Setter injection is acceptable but less common today

---

### 4. CoachContructorController — Constructor Injection Example

Source: `"src/main/java/com/spring/demo/main/controllers/CoachContructorController.java"`

#### GET `/coachConstructor`

- **Description**: Returns daily workout from Football Coach (default `Coach` bean)
- **Method**: GET
- **Auth**: Spring Security enabled
- **Request**: None
- **Response**: Plain text — `Practice football for 2 mins`
- **Dependency injection**: **Constructor injection** (recommended) — `FootBallCoach` via `@Primary`, no `@Qualifier` needed
- **Details**: Demonstrates the preferred DI style — dependencies are explicit and immutable
- **Learn**: `@Primary` resolves ambiguity when multiple `Coach` beans exist

#### GET `/swimCoach`

- **Description**: Returns daily workout from Swim Coach
- **Method**: GET
- **Auth**: Spring Security enabled
- **Request**: None
- **Response**: Plain text — `Practice swimming for 212 hours`
- **Dependency injection**: **Constructor injection** with `@Qualifier("swimCoach")`
- **Details**: `SwimCoach` is registered via `@Bean` in `"CoachConfig.java"`, not `@Component`
- **Learn**: `@Qualifier` picks a specific bean when `@Primary` is not the one you want

---

### 5. CoachConstructorSingletonTest — Singleton Scope

Source: `"src/main/java/com/spring/demo/main/controllers/CoachConstructorSingletonTest.java"`

#### GET `/testSingleton`

- **Description**: Checks whether two injected `cricketCoach` references are the same instance
- **Method**: GET
- **Auth**: Spring Security enabled
- **Request**: None
- **Response**: Plain text — `Are the two coaches the same? true`
- **Dependency injection**: Constructor injection — same `@Qualifier("cricketCoach")` twice
- **Details**: Default bean scope is singleton — both parameters receive the same object
- **Learn**: Singleton beans share one instance per Spring container

---

### 6. CoachConstructorPrototypeTest — Prototype Scope

Source: `"src/main/java/com/spring/demo/main/controllers/CoachConstructorPrototypeTest.java"`

#### GET `/testPrototype`

- **Description**: Checks whether two injected `golfCoach` references are the same instance
- **Method**: GET
- **Auth**: Spring Security enabled
- **Request**: None
- **Response**: Plain text — `Are the two coaches the same? false`
- **Dependency injection**: Constructor injection — same `@Qualifier("golfCoach")` twice
- **Details**: `"GolfCoach.java"` uses `@Scope(PROTOTYPE)` — new instance per injection point
- **Learn**: Prototype scope creates a fresh object each time Spring injects the bean

---

## Examples

```bash
# From "spring/1-spring-boot-setup/" — app running on port 8080
curl -u adminloco:PASSWORD http://localhost:8080/
curl -u adminloco:PASSWORD http://localhost:8080/custom
curl -u adminloco:PASSWORD http://localhost:8080/coachConstructor
curl -u adminloco:PASSWORD http://localhost:8080/swimCoach
curl -u adminloco:PASSWORD http://localhost:8080/testSingleton
curl -u adminloco:PASSWORD http://localhost:8080/testPrototype
```

If no password is set in `"application.properties"`, check the console at startup for the generated Spring Security password.

---

## Concepts

### Dependency injection comparison

| Style | Status | Example controller |
|-------|--------|-------------------|
| Field injection | Discouraged | `"CoachFieldController.java"` |
| Setter injection | Acceptable | `"CoachSetterController.java"` |
| Constructor injection | Recommended | `"CoachContructorController.java"` |

**Field injection (not recommended):**

```java
@Autowired
@Qualifier("cricketCoach")
private Coach coach;
```

**Setter injection (acceptable):**

```java
@Autowired
public void setCoach(@Qualifier("basketBallCoach") Coach coach) {
    this.coach = coach;
}
```

**Constructor injection (recommended):**

```java
@Autowired
public CoachContructorController(Coach coach, @Qualifier("swimCoach") Coach swimCoach) {
    this.controlledCoach = coach;
    this.anotherCoach = swimCoach;
}
```

### Coach implementations

| Bean | Scope / notes |
|------|----------------|
| `FootBallCoach` | `@Primary` — default when no qualifier |
| `BasketBallCoach` | Singleton `@Component` |
| `CricketCoach` | `@Lazy` singleton — used in `/testSingleton` |
| `SwimCoach` | Registered via `@Bean` in `"CoachConfig.java"` |
| `GolfCoach` | `@Lazy` + prototype — used in `/testPrototype` |

### Key concepts demonstrated

1. **Spring beans** — how Spring manages and instantiates components
2. **Dependency injection** — three approaches side by side
3. **Qualifiers** — `@Qualifier` resolves ambiguous dependencies
4. **Primary beans** — `@Primary` designates the default implementation
5. **Configuration classes** — `@Configuration` + `@Bean` for manual registration
6. **Lombok** — DTO with `@Data` for simplified code
7. **Property injection** — `@Value` loading from `"application.properties"`
8. **Bean scopes** — singleton vs prototype via HTTP demos

---

## Configuration

- **Bean configuration**: `"src/main/java/com/spring/demo/main/config/CoachConfig.java"` — defines the `swimCoach` bean
- **Application properties**: `"src/main/resources/application.properties"` — `dog.name`, `cat.name`, security user, lazy init
- **Qualifier usage**: Required when multiple `Coach` implementations exist and `@Primary` is not the intended bean

---

## Commands

Run the app from `"spring/1-spring-boot-setup/"`:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
.\mvnw.cmd spring-boot:run
```

Then call endpoints from the **Examples** section above.

---

## References

- Project structure and learning path: `"project_specification.md"`
- API deep-dive agent: `"agents/api-deep-dive.md"`
- Markdown rules: `"agents/documents.md"`
- [Spring Boot — Web](https://docs.spring.io/spring-boot/reference/web/servlet.html)
- [Spring Framework — REST controllers](https://docs.spring.io/spring-framework/reference/web/webmvc-controller.html)

---

## Notes / Caveats

- **Security:** `spring-boot-starter-security` is on the classpath. Endpoints require authentication unless you add a permissive security configuration.
- **Naming:** `CoachContructorController` is missing the second **s** in Constructor — use the actual class name when navigating source.
- **Lazy init:** `spring.main.lazy-initialization=true` may delay bean creation until first use — some coach constructors log on first request, not at startup.
