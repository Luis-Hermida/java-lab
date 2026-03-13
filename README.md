# Java Monorepo

## Description

This repository hosts multiple Java-based projects organized as a monorepo.

- **`apis/`**: Backend/API services. Each subfolder here can be a separate Java service.
- **`workbench/`**: Experimental/playground projects for learning.
- **`shared-utils/`**: Reusable Java libraries and packages shared across `apis/` and `workbench/` projects.
- **`agents/`**: Markdown-based agent definitions, documentation, and automation guides.

Each Java app or library will typically live in its own subdirectory with its own build configuration (e.g. Gradle or Maven). We can layer on shared build logic and conventions as the monorepo evolves.

## References

- **APIs**: `"apis/README.md"`
- **Workbench**: `"workbench/README.md"`
- **Shared utils**: `"shared-utils/README.md"`
- **Agents**: `"agents/README.md"`
