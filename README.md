# Java Monorepo

## Description

This repository hosts Java learning material and apps arranged as a single workspace. Each major area keeps its own build (Maven or plain `javac`) where appropriate.

## Layout

```text
java-lab/
├── .vscode/                 # Workspace / editor settings (e.g. Java tooling)
├── agents/                  # Markdown guides for docs and workbench conventions
├── spring/                  # Spring-related exercises
│   ├── 1-spring-boot-setup/   # Spring Boot sample app (Maven + wrapper)
│   └── 2-pending/             # Reserved for the next exercise (empty)
├── workbench/               # Small experiments and language playbooks
│   └── records-example/     # Runnable Java records example
├── .gitattributes
└── README.md
```

## Areas

- **`agents/`** — Markdown agent rules: how to write docs and workbench READMEs.
- **`spring/`** — Spring tutorials; each numbered folder is a self-contained project where present.
- **`workbench/`** — Experiments and PoCs; add one subfolder per topic with its own README and build.

## References

- **Agents:** [`agents/README.md`](agents/README.md)
- **Workbench:** [`workbench/README.md`](workbench/README.md)
- **Spring Boot sample:** [`spring/1-spring-boot-setup/`](spring/1-spring-boot-setup/) (Maven; use `mvnw` / `mvnw.cmd` locally)
