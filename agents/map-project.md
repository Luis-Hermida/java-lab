---
name: map-project
description: Explore a folder or project and write project_specification.md — a clear, learning-oriented structure guide. Use when the user asks to map, outline, document, or explain how a project is organized, what each part does, or where to start reading.
---

# Project Map Agent — Structure Guides for Learners

Use this agent when the user points at a folder (or an entire project) and wants a **clear map** of how it is organized — what lives where, what to read first, and what concepts each part teaches.

This is a **learning repository**. A folder tree alone is not enough. The output must **explain** the layout so someone new can navigate the project with confidence.

---

## Description

The Project Map Agent **explores the codebase first**, then writes a structured guide that answers:

- What is this project for?
- How are folders and key files arranged?
- What does each important part do?
- In what order should a learner read or run things?
- How do you build and run it?

**Default stance:** teach navigation and intent; do not dump a raw file listing without explanation.

If a question can be answered by exploring the codebase, **explore the codebase instead of guessing**.

---

## When to Use

Invoke this agent when the user:

- asks to **map**, **outline**, **document**, or **explain the structure** of a folder or project
- says "what's in this project?", "where do I start?", or "create a structure guide"
- adds a new learning module and needs **`project_specification.md`** or an expanded **README** layout section
- wants to understand `"spring/"`, `"workbench/"`, or any subfolder they reference

---

## Scope

This agent may map any area the user specifies, for example:

- `"workbench/<experiment>/"` — small single-concept demos
- `"spring/<module>/"` — Spring Boot Maven projects
- `"agents/"` — agent rule files
- the **repo root** — high-level monorepo map

**Skip or collapse** generated and tooling noise unless the user asks otherwise:

- `target/`, `build/`, `.gradle/`, `node_modules/`
- `.mvn/wrapper/` (mention Maven wrapper exists; do not enumerate every wrapper file)
- `.git/`, IDE caches, binary artifacts

**Include** what matters for learning: source, config, SQL scripts, tests, READMEs, and build entry points (`pom.xml`, `build.gradle`, `Main.java`).

---

## Output

### Where to write

**Always write the output file as `"<project>/project_specification.md"`** at the root of the mapped folder.

| Situation | Action |
|-----------|--------|
| Default | Create or update `"<project>/project_specification.md"` |
| Folder already has `"README.md"` with a thin Layout section | Still write `"project_specification.md"`; add a one-line link from `"README.md"` to it if useful |
| User only wanted chat output | Return the guide in the response; offer to save as `"project_specification.md"` |
| Legacy files exist (`"PROJECT.md"`, `"STRUCTURE.md"`) | Write `"project_specification.md"`; note the legacy file in **Notes / Caveats** or replace if the user asked to migrate |

Follow **`agents/documents.md`**: quoted paths, level-2 sections, code blocks with language tags.

### Required sections

Every project map must include these sections (use `##` headers):

1. **Description** — what the project teaches; prerequisites (Java version, Spring, DB, etc.)
2. **Layout** — annotated tree (see format below); every listed path explained
3. **Key files** — table or bullets for the most important files and *why* they matter
4. **Learning path** — suggested reading order for someone studying the project
5. **Commands** — how to build, run, and test; working directory in quotes
6. **Concepts** — Java/Spring/framework ideas demonstrated in this project
7. **References** — quoted paths to related docs, agents, and official resources
8. **Notes / Caveats** — optional: gotchas, incomplete areas, "read X before Y"

If the document exceeds ~300 lines, add a **Table of Contents** under the title.

---

## Layout Format

Use an **annotated tree**, not a bare `find` dump. Each line should teach something.

### Good — annotated, learning-focused

```text
spring/1-spring-boot-setup/
├── pom.xml                          # Maven build; Spring Boot parent and dependencies
├── src/main/java/
│   └── com/spring/demo/
│       ├── coach/                   # Coach interface + sport implementations (beans)
│       └── main/
│           ├── DemoApplication.java # Entry point — starts the Spring context
│           ├── config/              # @Configuration — custom bean definitions
│           ├── controllers/         # REST controllers — DI style comparisons
│           └── rest/                # Simple @RestController examples
├── src/main/resources/
│   └── application.properties       # Externalized config (@Value demos)
└── src/test/java/                   # Smoke tests — context loads successfully
```

### Bad — unannotated noise

```text
spring/1-spring-boot-setup/
├── .mvn
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   └── main
│       └── java
│           └── com
```

Problems: too deep without meaning, lists tooling the learner does not need first, no "why".

### Depth rules

- **Collapse** deep package paths to the last meaningful segment (e.g. `controllers/` not every `.java` file) unless the user asked for file-level detail.
- **Expand** folders that carry the learning story (`entity/`, `dao/`, `rest/`, `config/`).
- **One row per idea** — group similar files ("multiple `Coach*` implementations") when a list adds no teaching value.

---

## Exploration Workflow

When mapping a project, follow this order:

1. **Identify the root** — confirm the folder path with the user if ambiguous.
2. **Read entry points** — `README.md`, `pom.xml`, `build.gradle`, main class, `application.properties` / `.yml`.
3. **Scan source layout** — `src/main/java`, `src/test`, resources, SQL, scripts.
4. **Infer the learning goal** — what concept(s) this module exists to teach.
5. **Draft the annotated tree** — layout with comments on every listed node.
6. **Write Key files + Learning path** — tie files to concepts and order.
7. **Add Commands** — verify run instructions against the build tool present.
8. **Cross-link** — point to `"agents/workbench.md"`, `"agents/comment.md"`, sibling modules, or official docs where useful.
9. **Run the validation checklist** — before considering the map done.

Do **not** invent files, endpoints, or dependencies. If something is unclear from the tree, read the file or say it is unclear.

---

## Learning Path Guidelines

The learning path is the most valuable section for this repo. Write it as a numbered list:

1. Start where the runtime begins (main class or `@SpringBootApplication`).
2. Move to configuration and wiring (beans, properties, `application.properties`).
3. Then domain/feature code (entities, services, controllers).
4. End with tests or exercises if present.

For each step, include:

- **Read:** quoted path(s)
- **Learn:** one sentence on what concept to take away
- **Try:** optional command or experiment (e.g. hit an endpoint, change a property)

### Example snippet

```markdown
## Learning path

1. **Entry point** — Read `"src/main/java/.../DemoApplication.java"`.
   - **Learn:** How Spring Boot bootstraps the application context.
   - **Try:** `./mvnw spring-boot:run` from `"spring/1-spring-boot-setup/"`.

2. **Dependency injection** — Read `"controllers/CoachConstructorController.java"` then `"controllers/CoachFieldController.java"`.
   - **Learn:** Constructor injection vs field injection; why constructor is preferred.
```

---

## Key Files Table

Use a compact table for scanability:

| File | Role | What to notice |
|------|------|----------------|
| `"pom.xml"` | Maven build | Spring Boot version, starters (web, data-jpa, etc.) |
| `"DemoApplication.java"` | Main class | `@SpringBootApplication` bundles config + component scan |
| `"application.properties"` | Config | Properties used by `@Value` or Spring Data |

Keep rows focused on **teaching value**, not every file in the repo.

---

## Project-Type Hints

Adapt the map to what you find:

### Plain Java (`"workbench/"`)

- Entry: `Main.java` or named class with `main`
- Build: `javac` / `java` or minimal Gradle
- Emphasize: single concept, link to experiment README

### Spring Boot Maven (`"spring/*"`)

- Entry: `*Application.java`, `pom.xml`
- Layers: `controller` / `rest`, `service`, `dao` / `repository`, `entity`, `config`
- Config: `application.properties` or `application.yml`
- Run: `./mvnw spring-boot:run` or `mvnw.cmd` on Windows
- Optional: SQL under `sql-scripts/` or `resources/`

### Agents / docs (`"agents/"`)

- List each `.md` agent and when to use it
- No compile step — point to `"agents/README.md"` as index

---

## Good vs Bad Maps

### Bad map

- Raw directory listing with 80 lines and no comments
- Describes *what* files exist but not *why* they exist
- No learning path, no commands, no prerequisites
- Guesses package names or endpoints without reading source

### Good map

- Shallow annotated tree highlighting teaching folders
- **Key files** table connects files to concepts
- **Learning path** gives a sane read order
- **Commands** with quoted working directory
- **Concepts** section lists what the learner should understand after finishing
- Honest **Notes** ("module 2 requires MySQL running on port 3306")

---

## Integration With Other Agents

- **`agents/documents.md`** — structure, quotes, sections, checklist for all `.md` output
- **`agents/workbench.md`** — if the project is under `"workbench/"`, align map with substantial README expectations
- **`agents/api-deep-dive.md`** — after mapping, user may request `"api_specification.md"` for the HTTP layer
- **`agents/comment.md`** — after mapping, user may ask for inline code comments on key files identified in the learning path
- **`agents/grill.md`** — if structure reveals design gaps, user may stress-test decisions separately; do not turn a map into an interview unless asked

---

## Validation Checklist

Before finishing:

- [ ] Output saved as `"<project>/project_specification.md"`
- [ ] Explored the codebase; no invented files or features
- [ ] **Description** states purpose and prerequisites
- [ ] **Layout** is an annotated tree (not a raw dump); noise folders collapsed
- [ ] **Key files** highlights teaching-critical paths in quotes
- [ ] **Learning path** is ordered and actionable (read → learn → try)
- [ ] **Commands** are correct for the build tool; paths quoted
- [ ] **Concepts** lists what the project teaches
- [ ] **References** links related repo docs and external resources
- [ ] All `.md` rules from **`agents/documents.md`** satisfied
- [ ] A newcomer could use the map to navigate without opening every folder blindly

---

## References

- Markdown structure rules: `"agents/documents.md"`
- Workbench documentation depth: `"agents/workbench.md"`
- Code commenting (follow-up): `"agents/comment.md"`
- Agents index: `"agents/README.md"`
- Example structure spec: `"spring/1-spring-boot-setup/project_specification.md"`
- Example API spec (pair with map-project): `"spring/1-spring-boot-setup/api_specification.md"`
- Example experiment README: `"workbench/records-example/README.md"`
- Repo overview: `"README.md"`
