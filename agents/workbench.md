# Java Workbench Agent — Experimental Java Features

Use this agent when creating or modifying files in the Java workbench area of the repository. This area is intended for experimenting with language features, APIs, and small prototypes.

---

## Description

The Workbench Agent is responsible for creating small, focused experiments to explore features of Java. Code created here is not production code and should prioritize clarity and learning over architectural completeness.

This workspace exists to:

- test new Java features
- try small API patterns
- experiment with libraries
- create minimal runnable examples

The **workbench is a learning section** of the repo. A simple implementation is not enough: each experiment must be accompanied by **substantial documentation**. The goal is to teach the feature—through explanation, examples, and context—not just to show runnable code.

---

## Scope

This agent should operate **only** in the workbench area:

- `workbench/`

### Examples

- `workbench/*`

The agent **must not** modify production areas:

- `services/`
- `apis/`
- `shared-libs/`

---

## Guidelines

When creating experiments:

- Keep **code** small and focused; make **documentation** thorough. Learning comes from the docs as much as from the code.
- Each experiment demonstrates one concept, but the docs should explain that concept in depth (what it is, when to use it, how it works, pitfalls).
- Prefer single-file examples unless multiple files are necessary.
- Avoid unnecessary frameworks unless the experiment requires them.
- Prioritize **documentation quality and quantity**: if in doubt, add more explanation, more examples, and more references.

### Example focus areas

- Streams
- Records
- Pattern matching
- Concurrency
- Collections
- File I/O
- New Java language features

---

## File Structure

Each experiment should follow a simple structure:

```
workbench/
├── streams-example/
│   ├── Main.java
│   └── README.md
```

**Rules:**

- `Main.java` (or the main entry file) should be runnable.
- **Include rich documentation** for each experiment: not just a short README, but enough material to **learn** the feature. See the **Documentation** section below for required content.
- Add comments in code where they help; the main learning content lives in the docs.
- Validate all `.md` against **`agents/documents.md`** (file refs in quotes, clear sections).

---

## Documentation

Because **`workbench/` is for learning**, each experiment must include **substantial documentation**. A brief “what this is” plus a run command is not enough. Aim for material that stands on its own as a learning resource.

### Required documentation content

For every new experiment or feature, provide:

1. **Description**
   - What the Java feature or concept is and why it exists.
   - When to use it (and when not to).
   - How it fits with related language or API features (e.g. Records vs classes, Streams vs loops).

2. **Explanation / How it works**
   - How the feature works under the hood or at the language level (as appropriate).
   - Key rules or constraints (e.g. record components, immutability, generated methods).
   - Optional: comparison with alternatives (e.g. before/after, or “Records vs POJOs”).

3. **Examples**
   - Inline code snippets in the doc that illustrate the feature (not only the runnable `Main.java`).
   - At least one “minimal” example and, if useful, a slightly more realistic or nuanced one.
   - Brief commentary under each snippet explaining what to notice.

4. **Commands**
   - How to compile and run (e.g. `javac Main.java`, `java Main`, or `./gradlew run`).
   - Any prerequisites (Java version, working directory). Use quoted paths where relevant (e.g. `"workbench/records-example/"`).

5. **Pitfalls / Caveats / Notes**
   - Common mistakes, limitations, or gotchas.
   - Version or compatibility notes (e.g. “Requires Java 16+”).
   - Optional: links to official JEPs, specs, or docs.

6. **References**
   - Quoted paths to the experiment’s source (e.g. `"workbench/records-example/Main.java"`).
   - Links or paths to official docs, JEPs, or other learning resources.
   - Related repo docs: `"agents/workbench.md"`, `"agents/documents.md"`, `"workbench/README.md"` as applicable.

### Format and structure

- Put this content in a `README.md` in the experiment folder (e.g. `workbench/records-example/README.md`). If one file grows too long, add extra `.md` files (e.g. `notes.md`, `examples.md`) and reference them from the README.
- Use level-2 headers for the main sections above; use level-3 for sub-sections. Keep a clear hierarchy.
- Follow **`agents/documents.md`**: all file and path references in quotes or backticks, consistent list formatting, code blocks with language tags.
- Prefer concise paragraphs and lists; avoid walls of text, but do not sacrifice clarity for brevity—learning value comes first.

---

## Code Style

For experiments:

- Prefer simple and readable code.
- Use clear method names.
- Add brief comments explaining the feature being demonstrated.
- Avoid unnecessary abstraction.

### Example

```java
public class Main {
    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4);

        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(System.out::println);
    }
}
```

---

## Commands

Typical commands for running experiments:

```bash
javac Main.java
java Main
```

Or if using Gradle:

```bash
./gradlew run
```

---

## Validation Checklist

Before finishing an experiment:

- [ ] The example compiles and runs.
- [ ] The code clearly demonstrates the intended Java feature.
- [ ] **Documentation (substantial):** A `README.md` (and any extra docs) exists and includes:
  - [ ] **Description** — what the feature is, when to use it, how it relates to alternatives.
  - [ ] **Explanation / How it works** — key rules, constraints, generated behavior.
  - [ ] **Examples** — code snippets in the doc with brief commentary (not only the runnable app).
  - [ ] **Commands** — how to build/run and prerequisites; paths in quotes.
  - [ ] **Pitfalls / Caveats / Notes** — gotchas, version requirements, optional links to JEPs/official docs.
  - [ ] **References** — quoted paths to source and related docs; external links where helpful.
- [ ] All `.md` validated against **`agents/documents.md`** (structure, file refs in quotes).
- [ ] The doc is **learning-oriented**: someone new to the feature could use it to understand and learn, not just to run the code.

---

## References

- Java documentation: [Official Java Docs](https://docs.oracle.com/en/java/)
- Repo root: `"README.md"`
- Agents index: `"agents/README.md"`
- Document / markdown rules (for validating `.md` in workbench): `"agents/documents.md"`
