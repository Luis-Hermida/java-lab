# Agents

## Description

This directory contains Markdown-based “agent” rule files and learning guidelines for the repo.

Use these documents to instruct how code and documentation should be created or validated across different areas (for example, the Java [`workbench/`](../workbench/) learning space).

## Layout

```text
agents/
├── README.md
├── comment.md      # Educational commenting rules for Java learning code
├── documents.md    # Structure and quality rules for Markdown in this repo
├── grill.md        # Stress-test plans and designs via relentless Q&A
├── map-project.md  # Explore a folder and write a learning-oriented structure guide
└── workbench.md    # Expectations for workbench experiments and their READMEs
```

## Examples

- **`comment.md`** — Rules for adding educational comments to Java code (teach why, not what; good/bad examples).
- **`documents.md`** — Rules for writing well-structured `.md` files (sections, references, validation checklist).
- **`grill.md`** — Interview-style agent to stress-test a plan or design until decisions are clear.
- **`map-project.md`** — Explore a project folder and produce an annotated structure guide with learning path and commands.
- **`workbench.md`** — Rules for Java experiments under `workbench/` (scope, documentation expectations, and validation).

## Commands

There are no executable commands in this directory; these files are consumed as guidance.

To use an agent:

1. Open the relevant agent file (for example `workbench.md`).
2. When writing a Markdown doc, align with the structure required in `documents.md`.
3. When writing a workbench experiment README, follow the substantive documentation rules in `workbench.md`.

## References

- Repo root: [`README.md`](../README.md)
- Code comment rules: [`comment.md`](comment.md)
- Markdown rules: [`documents.md`](documents.md)
- Grill / design Q&A: [`grill.md`](grill.md)
- Project structure maps: [`map-project.md`](map-project.md)
- Workbench rules: [`workbench.md`](workbench.md)
