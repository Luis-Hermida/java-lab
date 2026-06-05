# Spring Boot Setup — Project Map

## Description

`"spring/1-spring-boot-setup/"` is the first Spring module in this learning repo. It is a small Spring Boot web app that teaches **how a project is laid out** and **how Spring wires components together**.

You will see:

- A standard Maven + Spring Boot project skeleton
- `@RestController` endpoints and externalized configuration
- Three dependency-injection styles (field, setter, constructor) side by side
- Spring beans: `@Component`, `@Primary`, `@Qualifier`, `@Configuration` + `@Bean`
- Bean lifecycle hooks (`@PostConstruct`, `@PreDestroy`) and scopes (singleton vs prototype)
- Lazy initialization at app and bean level
- Lombok for a minimal DTO

**Prerequisites:** Java 21 (see `"pom.xml"`), basic Java, and familiarity with running a Maven project. No database required.

For endpoint-level detail, see `"api_specification.md"`.

---

## Layout

```text
spring/1-spring-boot-setup/
├── pom.xml                              # Maven build — Spring Boot 4.0.5, Java 21, starters
├── project_specification.md             # This file — structure, learning path, key files
├── api_specification.md                 # Endpoint-by-endpoint API reference
├── mvnw / mvnw.cmd                      # Maven wrapper — run builds without a global Maven install
├── src/main/java/com/spring/demo/
│   ├── coach/                           # Domain “services” — Coach interface + sport implementations
│   │   ├── Coach.java                   # Shared contract (getDailyWorkout)
│   │   ├── FootBallCoach.java           # @Primary default bean; lifecycle demo (@PostConstruct)
│   │   ├── BasketBallCoach.java         # Used by setter-injection controller
│   │   ├── CricketCoach.java            # @Lazy component; singleton scope test
│   │   ├── GolfCoach.java               # @Lazy + @Scope(PROTOTYPE) — new instance per injection
│   │   └── SwimCoach.java               # Plain class — registered via @Bean in CoachConfig
│   └── main/
│       ├── DemoApplication.java         # Entry point — @SpringBootApplication + package scan
│       ├── config/
│       │   └── CoachConfig.java         # @Configuration — defines swimCoach bean manually
│       ├── controllers/                 # REST controllers comparing DI styles and bean scopes
│       │   ├── CoachFieldController.java
│       │   ├── CoachSetterController.java
│       │   ├── CoachContructorController.java
│       │   ├── CoachConstructorSingletonTest.java
│       │   └── CoachConstructorPrototypeTest.java
│       ├── rest/
│       │   └── HelloWorldController.java  # @Value property injection + Lombok DTO
│       └── dto/
│           └── SampleLombokDTO.java     # @Data — generated getters/setters
├── src/main/resources/
│   └── application.properties           # App name, Actuator info, security user, custom props
└── src/test/java/com/spring/demo/demo/
    └── DemoApplicationTests.java        # Smoke test — Spring context loads
```

Tooling folders (`.mvn/wrapper/`, `.vscode/`) exist for local dev but are omitted here — they are not part of the learning story.

---

## Key files

| File | Role | What to notice |
|------|------|----------------|
| `"pom.xml"` | Build & dependencies | Spring Boot **4.0.5**, Java **21**; starters: webmvc, actuator, security, devtools; Lombok |
| `"src/main/java/com/spring/demo/main/DemoApplication.java"` | Application entry | `@SpringBootApplication` scans `"com.spring.demo.coach"` and `"com.spring.demo.main"` |
| `"src/main/resources/application.properties"` | External config | Custom `dog.name` / `cat.name`; `spring.main.lazy-initialization=true`; Actuator + security settings |
| `"src/main/java/com/spring/demo/coach/Coach.java"` | Shared interface | Multiple implementations → need `@Qualifier` or `@Primary` |
| `"src/main/java/com/spring/demo/coach/FootBallCoach.java"` | Default coach bean | `@Primary` — injected when no qualifier; `@PostConstruct` / `@PreDestroy` logging |
| `"src/main/java/com/spring/demo/main/config/CoachConfig.java"` | Java-based config | `@Bean` method registers `SwimCoach` (not a `@Component`) |
| `"src/main/java/com/spring/demo/main/controllers/*.java"` | DI & scope demos | Field vs setter vs constructor injection; singleton vs prototype checks |
| `"src/main/java/com/spring/demo/main/rest/HelloWorldController.java"` | Simple REST | `@Value` reads properties; Lombok-backed DTO on `GET /` |
| `"api_specification.md"` | API deep-dive | Full endpoint list and DI code snippets — read after this map |

---

## Learning path

1. **Build and boot** — Skim `"pom.xml"` then read `"DemoApplication.java"`.
   - **Learn:** How Spring Boot starts and which packages get component-scanned.
   - **Try:** Run the app (see **Commands**). Watch the console for coach constructor and `@PostConstruct` logs.

2. **Configuration** — Read `"application.properties"`.
   - **Learn:** Externalized settings (`dog.name`, `cat.name`), lazy init at the context level, Actuator `info` metadata.
   - **Try:** Change `dog.name` and hit `GET /custom` after restart.

3. **Simple REST + properties** — Read `"rest/HelloWorldController.java"` and `"dto/SampleLombokDTO.java"`.
   - **Learn:** `@RestController`, `@Value`, and Lombok `@Data` for boilerplate-free DTOs.

4. **Beans and the Coach model** — Read `"coach/Coach.java"`, then `"FootBallCoach.java"`, `"CricketCoach.java"`, `"GolfCoach.java"`.
   - **Learn:** `@Component`, `@Primary`, `@Lazy`, and `@Scope(PROTOTYPE)`; why multiple `Coach` beans need disambiguation.

5. **Manual bean registration** — Read `"config/CoachConfig.java"` and `"coach/SwimCoach.java"`.
   - **Learn:** When to use `@Configuration` + `@Bean` instead of `@Component` on the class itself.

6. **Dependency injection styles** — Read controllers in this order:
   - `"controllers/CoachContructorController.java"` (preferred)
   - `"controllers/CoachSetterController.java"`
   - `"controllers/CoachFieldController.java"`
   - **Learn:** Constructor injection is explicit and test-friendly; field injection is shown but discouraged.

7. **Bean scopes** — Read `"CoachConstructorSingletonTest.java"` and `"CoachConstructorPrototypeTest.java"`.
   - **Learn:** Singleton (`cricketCoach`) → same instance (`==` true); prototype (`golfCoach`) → different instances (`==` false).
   - **Try:** `GET /testSingleton` and `GET /testPrototype`.

8. **API reference** — Read `"api_specification.md"` for every endpoint, response, and DI snippet in one place.

9. **Tests** — Read `"src/test/java/com/spring/demo/demo/DemoApplicationTests.java"`.
   - **Learn:** `@SpringBootTest` smoke test — verifies the context wiring without hitting HTTP.

---

## Examples

### Hit a few endpoints after startup

Default port is **8080**. Spring Security is on the classpath (`spring-boot-starter-security`); use the configured user from `"application.properties"` or check startup logs for the generated password if applicable.

```bash
# From spring/1-spring-boot-setup/ with the app running:
curl http://localhost:8080/
curl http://localhost:8080/custom
curl http://localhost:8080/coachConstructor
curl http://localhost:8080/swimCoach
curl http://localhost:8080/testSingleton
curl http://localhost:8080/testPrototype
```

### What singleton vs prototype looks like

- `GET /testSingleton` → `Are the two coaches the same? true` (`CricketCoach` is a singleton bean)
- `GET /testPrototype` → `Are the two coaches the same? false` (`GolfCoach` is prototype-scoped)

### Package scan is explicit

`DemoApplication` limits scanning to two roots — coach implementations live under `"com.spring.demo.coach"`, web layer under `"com.spring.demo.main"`:

```java
@SpringBootApplication(scanBasePackages = {
    "com.spring.demo.coach",
    "com.spring.demo.main"
})
```

---

## Commands

Run from `"spring/1-spring-boot-setup/"`.

**Windows (PowerShell):**

```bash
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
```

**Unix / macOS / Git Bash:**

```bash
./mvnw spring-boot:run
./mvnw test
```

**Package without running:**

```bash
./mvnw clean package
```

The app listens on port **8080** unless overridden. DevTools enables automatic restart on classpath changes during development.

---

## Concepts

| Concept | Where it appears |
|---------|------------------|
| Spring Boot auto-configuration | `"DemoApplication.java"`, `"pom.xml"` starters |
| Component scanning | `@SpringBootApplication(scanBasePackages = …)` |
| REST controllers | `"rest/"`, `"controllers/"` |
| Property injection (`@Value`) | `"HelloWorldController.java"`, `"application.properties"` |
| Field / setter / constructor DI | `"CoachFieldController"`, `"CoachSetterController"`, `"CoachContructorController"` |
| `@Primary` and `@Qualifier` | `"FootBallCoach"`, controllers requesting specific coaches |
| `@Configuration` and `@Bean` | `"CoachConfig.java"` → `SwimCoach` |
| Bean lifecycle | `@PostConstruct` / `@PreDestroy` on `"FootBallCoach"` |
| Lazy initialization | `spring.main.lazy-initialization` + `@Lazy` on some coaches |
| Singleton vs prototype scope | `"CoachConstructorSingletonTest"`, `"CoachConstructorPrototypeTest"`, `"GolfCoach"` |
| Lombok | `"SampleLombokDTO.java"` |
| Actuator & security starters | `"pom.xml"`, `"application.properties"` |

---

## References

- API reference (this project): `"api_specification.md"`
- Repo overview: `"README.md"`
- Project mapping agent: `"agents/map-project.md"`
- API deep-dive agent: `"agents/api-deep-dive.md"`
- Markdown rules used for this doc: `"agents/documents.md"`
- Next Spring modules: `"spring/2-spring-boot-hibernate-jpa-crud/"`, `"spring/3-spring-rest/"`
- [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Framework — Dependency Injection](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html)

---

## Notes / Caveats

- **Security:** `spring-boot-starter-security` is included. Expect authenticated access to web endpoints unless you add a security config that permits all — plan for basic auth when calling URLs locally.
- **Lazy init:** `spring.main.lazy-initialization=true` delays bean creation until needed; combine with `@Lazy` on beans for a deliberate startup profile — watch which constructors print on first request vs at boot.
- **Naming:** `CoachContructorController` is spelled without the second **s** in Constructor — match the actual class name when searching the repo.
- **Specs at project root:** `"project_specification.md"` (this file) and `"api_specification.md"` live at the module root for easy discovery.
- **Follow-up:** Use `"agents/comment.md"` to add teaching comments inline on key files from the learning path above.
