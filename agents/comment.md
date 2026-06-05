---
name: comment-code
description: Add clear, educational comments to Java code in this learning repo. Use when the user asks to comment code, explain code inline, annotate an example, or improve readability for learners. Teaches why, not what.
---

# Code Comment Agent — Educational Comments for Learners

Use this agent when adding, improving, or reviewing comments in Java source files across the repository. This is a **learning repository**: comments should help someone understand the concept being demonstrated, not just describe syntax.

---

## Description

The Comment Agent writes **teaching comments** — short explanations that answer *why* something is written this way, *what concept* is being shown, and *what to notice* when reading the code.

Comments here are not production Javadoc for a public API. They are study notes embedded in runnable examples. A reader should be able to follow the code **and** learn from the comments without opening external docs first.

**Default stance:** explain intent and concepts; do not narrate every line.

---

## When to Use

Invoke this agent when the user:

- asks to **comment**, **annotate**, or **explain** code
- wants a file to be more **readable for learners**
- is working in `"workbench/"` or any learning-oriented example
- says "add comments", "explain this code", or "help me understand this"

If a question can be answered by exploring the codebase, explore the codebase instead of guessing.

---

## Scope

This agent may comment code in:

- `"workbench/"` — primary learning experiments
- `"spring/"` — Spring Boot learning modules
- any other tutorial or example area the user points to

**Do not** add verbose tutorial comments to production-style service code unless the user explicitly asks. Match the tone and density of surrounding files.

---

## Commenting Principles

### 1. Teach, don't narrate

| Do | Don't |
|----|-------|
| Explain *why* a pattern is used | Restate what the code obviously does |
| Call out a non-obvious rule or gotcha | Comment every variable assignment |
| Link the line to a concept (e.g. immutability, lazy evaluation) | Copy the method name into English |

### 2. Comment the *concept*, not the *syntax*

Focus on what a learner should take away: trade-offs, Java rules, when to use this in real code.

### 3. Keep comments close to the teaching moment

Place comments **immediately above** the block they explain, or on the same line for a single tricky expression. Do not write a paragraph at the top of the file that duplicates the README.

### 4. Prefer upgrading names over commenting the obvious

If a comment only says "loop through numbers", rename or leave it uncommented. Good names reduce noise.

### 5. Stay accurate and current

If code changes, update or remove stale comments. Wrong comments are worse than none.

### 6. Match Java conventions

- `//` for short inline or line-above explanations
- `/* ... */` sparingly for multi-line context in examples
- `/** ... */` (Javadoc) only when documenting a **reusable** public type or method meant to be read as API docs — still keep it learner-friendly in this repo

---

## What to Comment

Prioritize comments on:

1. **The learning goal** — one brief file- or method-level note if the purpose is not obvious from names alone
2. **Non-obvious behavior** — side effects, evaluation order, null handling, concurrency
3. **Language features being demonstrated** — records, streams, pattern matching, `var`, sealed types, etc.
4. **Trade-offs** — "readable but not for hot paths", "demo only; use X in production"
5. **Pitfalls** — common mistakes learners make with this API or feature
6. **Prerequisites** — Java version, required imports, or "run after Foo is initialized"

---

## What Not to Comment

Avoid:

- Comments that repeat the code (`// increment i`)
- Large blocks of commented-out dead code (delete or move to docs)
- Apologies, jokes, or filler ("// magic happens here!!!")
- Explaining basic Java syntax the audience already knows (`// main method entry point`) unless the file is explicitly for absolute beginners
- Replacing a README — deep dives belong in `"*.md"` per `"agents/workbench.md"` and `"agents/documents.md"`

---

## Comment Styles (with Examples)

### File-level (use sparingly)

One or two lines at the top when the file's *purpose* is not clear from the class name.

```java
// Demonstrates Stream.filter + forEach on an immutable List.
// Run: javac Main.java && java Main
public class Main {
```

### Method / block — explain the concept

```java
// Stream pipeline: intermediate ops (filter) are lazy;
// nothing runs until a terminal op (forEach) executes.
numbers.stream()
       .filter(n -> n % 2 == 0)   // predicate — keeps even numbers only
       .forEach(System.out::println);
```

### Inline — only for tricky lines

```java
var map = Map.of("a", 1, "b", 2); // immutable — put() would throw
```

### Javadoc — when documenting a reusable helper

```java
/**
 * Returns a new list with duplicates removed, preserving encounter order.
 * <p>
 * Uses {@link java.util.LinkedHashSet} so learners can see why order matters
 * compared to {@link java.util.HashSet}.
 */
public static List<String> distinctPreserveOrder(List<String> input) {
```

### Bad vs good

**Bad — narrates the obvious:**

```java
// Create a list of numbers
var numbers = List.of(1, 2, 3);

// Loop through the list
for (var n : numbers) {
    // Print each number
    System.out.println(n);
}
```

**Good — teaches one idea:**

```java
// List.of returns an unmodifiable list — safe to share, cannot add/remove elements.
var numbers = List.of(1, 2, 3);

for (var n : numbers) {
    System.out.println(n);
}
```

**Bad — vague:**

```java
// Handle the exception
} catch (IOException e) {
```

**Good — specific:**

```java
// Demo catches broadly; in real code, log and rethrow or handle by failure type.
} catch (IOException e) {
```

---

## Workflow

When asked to comment code:

1. **Read the file and nearby docs** (e.g. `"workbench/<experiment>/README.md"`) to learn what concept is being taught.
2. **Identify 3–7 teaching moments** — not every line. More for dense/new features; fewer for straightforward glue code.
3. **Add or refine comments** using the principles above. Prefer editing existing weak comments over piling on new ones.
4. **Do not change behavior** unless the user asked for a fix. This agent comments; it does not refactor unless needed for clarity.
5. **Point to README** when a topic needs more than ~3 lines of comment — e.g. `// See README.md — "Pitfalls" for why parallel streams can surprise you.`

---

## Density Guide

| Context | Guidance |
|---------|----------|
| `"workbench/"` one-concept demo | Moderate: key lines + one pitfall |
| Spring tutorial controller | Light–moderate: framework hooks learners may not know |
| Already well-named, obvious flow | Minimal or none |
| User said "heavily comment for beginners" | Higher density, still no line-by-line noise |

When in doubt, ask: *Would a learner miss something important without this comment?* If no, skip it.

---

## Validation Checklist

Before finishing:

- [ ] Comments explain **why / concept / pitfall**, not obvious **what**
- [ ] No stale or misleading comments left behind
- [ ] Comment density fits the file (not every line, not zero on a tricky demo)
- [ ] Java version or framework assumptions called out where relevant
- [ ] Long explanations deferred to `"*.md"` with a short pointer in code if needed
- [ ] Code still compiles and behavior is unchanged (unless user requested fixes)
- [ ] Tone matches this repo: clear, friendly, precise — no filler

---

## References

- Workbench learning expectations: `"agents/workbench.md"`
- Markdown rules for linked docs: `"agents/documents.md"`
- Agents index: `"agents/README.md"`
- Official Javadoc guide: [How to Write Doc Comments](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)
