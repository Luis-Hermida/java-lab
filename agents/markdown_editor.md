# Markdown Editor Agent — Rules for .md Files

Use this agent when creating or editing Markdown (`.md`) files in the repo. Follow these rules so docs stay consistent and easy to navigate.

---

## Description

This agent enforces structure, references, and quality for all Markdown documentation. Apply these rules when writing or reviewing any `.md` file.

---

## File References

- **Always reference files explicitly.** When mentioning a file, path, or artifact:
  - Put the path in **quotes**, e.g. `"src/main/java/App.java"` or `"apis/README.md"`.
  - Prefer inline code for paths: `` `path/to/file` `` so they are clearly identifiable.
- **Cross-references:** When linking to another doc or file, use a proper link or at least the quoted path, e.g. `See "agents/README.md"` or `[agents/README.md](agents/README.md)`.
- **Avoid bare paths.** Do not leave unquoted paths like `apis/service/build.gradle`; use `` `apis/service/build.gradle` `` or `"apis/service/build.gradle"`.

---

## Required Sections (Structure)

Every substantive `.md` file should use clear, consistent sections. Use at least these where they apply:

| Section        | Purpose |
|----------------|--------|
| **Description** | What the doc or feature is about (short summary). |
| **Examples**    | Code snippets, sample commands, or usage examples. |
| **Commands**    | CLI commands, scripts, or steps to run (e.g. `gradle build`, `mvn test`). |
| **References**  | Links or quoted paths to related files, docs, or resources. |
| **Notes / Caveats** | Optional: gotchas, limitations, or follow-ups. |

- Use **level-2 headers** (`##`) for these main sections.
- Use **level-3 headers** (`###`) for sub-sections when needed.
- Keep a single blank line before and after headers.
- Order sections logically: Description first, then Examples/Commands, then References and Notes.

---

## Validation Checklist (Well-Written Content)

Before considering a `.md` file done, validate:

1. **Structure**
   - [ ] Has a clear title (level-1 `#` header).
   - [ ] Uses the standard sections above where relevant (Description, Examples, Commands, etc.).
   - [ ] Headers follow a clear hierarchy (`#` → `##` → `###`).

2. **References**
   - [ ] All file paths and artifact names are in quotes or backticks (e.g. `"path/to/file"` or `` `path/to/file` ``).
   - [ ] Cross-references to other docs or files are explicit (link or quoted path).

3. **Content**
   - [ ] No orphan bullets or empty sections; remove or fill them.
   - [ ] Code blocks have a language tag when possible (e.g. ` ```java `, ` ```bash `).
   - [ ] Lists use consistent formatting (all `-` or all `*`, consistent indentation).
   - [ ] Spelling and grammar are checked; sentences are complete.

4. **Commands**
   - [ ] Commands are in fenced code blocks or clearly marked.
   - [ ] Placeholders (e.g. `<project-name>`) are explained when used.

---

## Examples

### Good — file reference and sections

- Title and sections (Description, Commands, References) are present.
- Paths are quoted: `"apis/my-service"`, `"shared-utils/common"`, `"apis/my-service/src/main/resources/application.yml"`.
- Commands live in a fenced code block with a language tag (e.g. ` ```bash `).
- Cross-reference uses a link: `[APIs README](apis/README.md)`.

### Bad — bare path, no sections

- Path `apis/my-service` is not in quotes or backticks.
- No **Description**, **Commands**, or **References** sections.
- Commands (`gradlew run`) are not in a code block or clearly marked.

---

## Commands

When this agent is used to edit Markdown:

1. **On create:** Start with a level-1 title and add the standard sections (Description, Examples, Commands, References) as needed.
2. **On edit:** Ensure every added or touched file path is in quotes or backticks and that sections are present and ordered as above.
3. **On review:** Run through the Validation Checklist and fix any missing references, structure, or formatting.

---

## References

- Repo layout: `"README.md"`
- Agents index: `"agents/README.md"`
