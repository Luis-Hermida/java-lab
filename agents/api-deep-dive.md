---
name: api-deep-dive
description: Explore REST controllers in a Spring or Java project and write api_specification.md — endpoint-by-endpoint API documentation with learning context. Use when the user asks for API docs, endpoint reference, REST deep-dive, or to document controllers.
---

# API Deep Dive Agent — Endpoint Documentation for Learners

Use this agent when a project already has (or needs) **endpoint-level documentation** — what each route does, how it is wired in Spring, what to call with `curl`, and what concept each endpoint teaches.

This complements **`agents/map-project.md`**: the project map explains *where files live*; this agent explains *what each HTTP endpoint does and why*.

**Reference example:** `"spring/1-spring-boot-setup/api_specification.md"`

If a detail can be answered by reading the codebase, **read the codebase instead of guessing**.

---

## Description

The API Deep Dive Agent **reads every `@RestController` (and similar web entry point)** in the target project, then writes a learning-oriented API reference that covers:

- Each endpoint: method, path, response, and teaching purpose
- How dependencies are injected for that controller
- Sample requests (`curl`) and expected responses
- Cross-cutting concepts (beans, qualifiers, security, validation) tied to real routes
- Source file paths so learners can jump from doc to code

**Default stance:** document endpoints as **lessons**, not as a bare OpenAPI dump. Explain what the learner should notice.

---

## When to Use

Invoke this agent when the user:

- asks for **API documentation**, **endpoint reference**, or **REST deep-dive**
- says "document the controllers", "what endpoints exist?", or "explain the API"
- has **`project_specification.md`** from `map-project` and wants the HTTP layer documented next
- points at a Spring module under `"spring/"` or any project with REST controllers

**Do not use for:**

- Folder layout only → use **`agents/map-project.md`**
- Inline code comments → use **`agents/comment.md`**

---

## Scope

Explore and document:

- `@RestController`, `@Controller` + `@ResponseBody`
- Route annotations: `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@RequestMapping`
- Request params, path variables, request bodies (when present)
- Related DTOs, services, and config that shape responses
- Security rules that affect calling endpoints (basic auth, CSRF, etc.)

**Read from source** — do not invent paths, status codes, or JSON shapes.

---

## Output

### Where to write

**Always write the output file as `"<project>/api_specification.md"`** at the root of the target project.

| Situation | Action |
|-----------|--------|
| Default | Create or update `"<project>/api_specification.md"` |
| Project already has `"project_specification.md"` | Cross-link both files; do not duplicate the folder layout |
| User asked for chat only | Return the doc in the response; offer to save as `"api_specification.md"` |
| Legacy files exist (`"src/main/project.md"`, `"API.md"`) | Write `"api_specification.md"`; note the legacy file in **Notes / Caveats** or replace if the user asked to migrate |

Follow **`agents/documents.md`**: level-1 title, `##` sections, quoted paths, fenced code blocks with language tags.

### Relationship to other docs

| Doc | Role |
|-----|------|
| `"project_specification.md"` | Structure, learning path, key files — from `map-project` |
| `"api_specification.md"` | **This agent** — endpoint deep-dive |
| `"README.md"` | Short module intro; link to both specs above |

Cross-link: API doc should say *"For folder layout and read order, see `"project_specification.md"`."*

---

## Required Sections

Every API deep-dive must include:

1. **Description** (or **Project overview**) — what the API teaches; base URL and prerequisites
2. **API endpoints** — grouped by controller; one subsection per route
3. **Examples** — `curl` (or HTTP client) samples with expected response bodies
4. **Concepts** — patterns demonstrated across endpoints (DI styles, beans, DTOs, etc.)
5. **Configuration** — properties, security, beans that affect the API
6. **Commands** — run the app + call endpoints; working directory in quotes
7. **References** — quoted controller paths, related docs, official Spring REST links
8. **Notes / Caveats** — auth requirements, typos in class names, incomplete endpoints

Optional when relevant: **Request/response schemas**, **Error responses**, **Controller index table**.

---

## Endpoint Entry Template

Group endpoints **by controller class**. For each route, use this template (adapt fields that do not apply):

```markdown
### N. ClassName — short purpose

Source: `"src/main/java/.../ClassName.java"`

#### GET `/path`

- **Description**: What this route does and what concept it demonstrates
- **Method**: GET | POST | PUT | DELETE | PATCH
- **Auth**: None | Basic auth | Other — only if security applies
- **Request**: Path vars, query params, headers, body (or "None")
- **Response**: Type and example body (plain text or JSON)
- **Dependency injection**: How this controller gets its dependencies (constructor, field, `@Value`, etc.)
- **Details**: Bean names, `@Qualifier`, `@Primary`, service layer calls — tie doc to source
- **Learn**: One sentence — what a student should take away
- **Note**: Best-practice callout, deprecation, or "not recommended" (optional)
```

### Good endpoint entry (learning-focused)

```markdown
#### GET `/coachConstructor`

- **Description**: Returns the daily workout from the default `Coach` bean (`FootBallCoach` via `@Primary`)
- **Method**: GET
- **Auth**: Spring Security enabled — use credentials from `"application.properties"`
- **Request**: None
- **Response**: Plain text, e.g. `Practice football for 2 mins`
- **Dependency injection**: **Constructor injection** (recommended) — `Coach` injected without `@Qualifier` because `@Primary` resolves the default
- **Details**: See `"CoachContructorController.java"` constructor; compare with `"CoachFieldController.java"` for field injection
- **Learn**: Constructor injection makes dependencies explicit and is the preferred style in modern Spring
```

### Bad endpoint entry

```markdown
#### GET `/coachConstructor`

Returns football workout.
```

Problems: no method/auth/DI context, no source path, no learning takeaway, no sample response.

---

## Workflow

1. **Confirm project root** — e.g. `"spring/1-spring-boot-setup/"`.
2. **Find web layer** — search for `@RestController`, `@Controller`, `*Controller.java`.
3. **Read each controller** — mappings, injected dependencies, return types.
4. **Read supporting code** — DTOs, services, `"application.properties"`, security config.
5. **Run or infer base URL** — default `http://localhost:8080` unless configured otherwise.
6. **Draft endpoint sections** — use the template above; order controllers logically (simple REST first, then DI demos).
7. **Add Concepts section** — synthesize patterns (see reference `"api_specification.md"` DI section).
8. **Add Examples** — `curl` for representative endpoints; note auth flags if needed.
9. **Save** — write `"<project>/api_specification.md"`.
10. **Cross-link** — `"project_specification.md"`, controller source paths, `"agents/map-project.md"`.
11. **Validation checklist** — before finishing.

---

## Concepts Section (required synthesis)

After listing endpoints, add a **Concepts** (or **Key concepts demonstrated**) section that groups ideas **across** controllers — not repeated per endpoint.

Draw from what the reference doc does well in `"spring/1-spring-boot-setup/api_specification.md"`:

### Dependency injection comparison

Document three styles with **real code from the project**:

| Style | Status | Example controller | Snippet source |
|-------|--------|-------------------|----------------|
| Field injection | Discouraged | `"CoachFieldController.java"` | `@Autowired` on field |
| Setter injection | Acceptable | `"CoachSetterController.java"` | `@Autowired` on setter |
| Constructor injection | Recommended | `"CoachContructorController.java"` | Constructor params |

Include fenced `java` blocks copied or paraphrased from actual controllers.

### Other concept groups (when present)

- **Beans & qualifiers** — multiple implementations, `@Primary`, `@Qualifier`, `@Bean` in config
- **Scopes** — singleton vs prototype endpoints (e.g. `/testSingleton`, `/testPrototype`)
- **Property injection** — `@Value` and `"application.properties"`
- **DTOs & Lombok** — response shaping without boilerplate
- **Lifecycle** — `@PostConstruct` visible in logs when hitting certain beans

---

## Examples Section

Provide runnable samples. Always state the working directory and port.

```bash
# From "spring/1-spring-boot-setup/" — app running on port 8080
curl -u adminloco:PASSWORD http://localhost:8080/
curl -u adminloco:PASSWORD http://localhost:8080/custom
curl -u adminloco:PASSWORD http://localhost:8080/coachConstructor
curl -u adminloco:PASSWORD http://localhost:8080/testSingleton
```

If security generates a password at startup, say: *check the console for the generated password* rather than inventing one.

Include **expected output** for at least 2–3 endpoints so learners can verify behavior.

---

## Controller Index Table (recommended)

At the top of **API endpoints**, add a quick-scan table:

| Controller | Base paths | Teaches |
|------------|------------|---------|
| `"HelloWorldController"` | `/`, `/custom` | `@Value`, Lombok DTO |
| `"CoachFieldController"` | `/coachField` | Field injection (discouraged) |
| `"CoachContructorController"` | `/coachConstructor`, `/swimCoach` | Constructor injection, `@Qualifier` |

---

## Good vs Bad API Docs

### Good (like the reference, improved)

- Endpoints grouped by controller with source paths in quotes
- Each route: method, response example, DI style, learning takeaway
- **Concepts** section with code snippets from real controllers
- **Configuration** ties API behavior to `"application.properties"` and config classes
- **curl** examples and auth caveats
- Links to `"project_specification.md"` for structure — no duplicate folder trees

### Bad

- OpenAPI-style path list with no explanations
- Endpoints documented without reading controllers (wrong paths or methods)
- No DI or Spring context — reads like generic REST docs
- Duplicates entire **Layout** section from `map-project`
- Missing **Commands** or **References**

---

## Integration With Other Agents

| Agent | Role |
|-------|------|
| **`agents/map-project.md`** | Run first for `project_specification.md`; API doc links back to it |
| **`agents/documents.md`** | Markdown structure, quotes, validation |
| **`agents/comment.md`** | Optional follow-up — teaching comments in controller source |
| **`agents/grill.md`** | Optional — stress-test API design decisions separately |

---

## Validation Checklist

Before finishing:

- [ ] Every `@RestController` in scope is represented (or explicitly marked "no HTTP mappings")
- [ ] Endpoint paths and HTTP methods match source code
- [ ] Each endpoint entry includes **Learn** (or equivalent teaching takeaway)
- [ ] **Concepts** section synthesizes DI, beans, or other patterns with code snippets
- [ ] **Examples** include `curl` (or similar) with quoted project path and port
- [ ] **Configuration** documents properties/security that change API behavior
- [ ] **References** list quoted controller paths and related docs
- [ ] Output saved as `"<project>/api_specification.md"`
- [ ] Cross-link to `"project_specification.md"` if it exists; no duplicated folder map
- [ ] All `.md` rules from **`agents/documents.md`** satisfied
- [ ] A learner could call endpoints and understand *why* each controller is written that way

---

## References

- Reference API spec (module 1): `"spring/1-spring-boot-setup/api_specification.md"`
- Canonical API output filename: `"<project>/api_specification.md"`
- Canonical structure output filename: `"<project>/project_specification.md"`
- Project mapping agent: `"agents/map-project.md"`
- Markdown rules: `"agents/documents.md"`
- Agents index: `"agents/README.md"`
- [Spring Boot — Web](https://docs.spring.io/spring-boot/reference/web/servlet.html)
- [Spring Framework — REST controllers](https://docs.spring.io/spring-framework/reference/web/webmvc-controller.html)
