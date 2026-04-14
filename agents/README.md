# Agents

## Description

This directory contains Markdown-based “agent” rule files and learning guidelines for the repo.

Use these documents to instruct how code and documentation should be created or validated across different areas (for example, the Java [`workbench/`](../workbench/) learning space).

## Layout

```text
agents/
├── README.md
├── documents.md    # Structure and quality rules for Markdown in this repo
└── workbench.md    # Expectations for workbench experiments and their READMEs
```

## Examples

- **`documents.md`** — Rules for writing well-structured `.md` files (sections, references, validation checklist).
- **`workbench.md`** — Rules for Java experiments under `workbench/` (scope, documentation expectations, and validation).

## Commands

There are no executable commands in this directory; these files are consumed as guidance.

To use an agent:

1. Open the relevant agent file (for example `workbench.md`).
2. When writing a Markdown doc, align with the structure required in `documents.md`.
3. When writing a workbench experiment README, follow the substantive documentation rules in `workbench.md`.

## References

- Repo root: [`README.md`](../README.md)
- Markdown rules: [`documents.md`](documents.md)
- Workbench rules: [`workbench.md`](workbench.md)
