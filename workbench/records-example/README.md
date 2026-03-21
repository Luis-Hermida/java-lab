# Java Records — Learning Example

## Description

**Java Records** (finalized in Java 16, JEP 395) are a way to model **immutable data** with minimal boilerplate. Instead of writing a class with private final fields, a constructor, getters, `equals`, `hashCode`, and `toString`, you declare the components once; the compiler generates the rest.

**When to use records**

- DTOs, configuration holders, result types, or any value that is “just data.”
- When you want immutable, transparent data carriers with consistent `equals`/`hashCode`/`toString` based on components.

**When not to use records**

- When you need mutability, inheritance (records are final), or heavy encapsulation. Use a regular class instead.

**How they fit in**

- Records sit between “plain data” and “rich domain objects”: more structured than a tuple or map, less behavior than a full class. They complement **pattern matching** (e.g. `instanceof` and `switch` on records) and **sealed classes** for modeling data in modern Java.

---

## How It Works

- **Components** — The list in parentheses (e.g. `String name, int age`) defines the record’s state. Each component becomes a `private final` field and a public **accessor** with the same name (e.g. `name()`, `age()`), not `getName()`/`getAge()`.
- **Generated members** — The compiler generates a **canonical constructor** (all components), `equals(Object)`, `hashCode()`, and `toString()` based on the components. You can override any of these if needed.
- **Compact constructor** — A constructor with no parameter list that runs before the fields are assigned. Use it to validate or normalize (e.g. trim strings, reject invalid values). You assign to the implicit parameters by name; you cannot assign to `this` (records are immutable).
- **Constraints** — Records are implicitly `final` and extend only `java.lang.Record`. Components cannot be reassigned after construction.

---

## Examples

### Minimal record

No custom constructor; only the generated one.

```java
record Point(int x, int y) {}

var p = new Point(1, 2);
System.out.println(p.x() + ", " + p.y());  // 1, 2
System.out.println(p);                      // Point[x=1, y=2]
```

Notice: accessors are `x()` and `y()`, and `toString()` is generated.

### Record with compact constructor

Validation and normalization without listing every parameter.

```java
record Person(String name, int age) {
    public Person {
        if (age < 0) throw new IllegalArgumentException("age must be >= 0");
        name = name != null ? name.strip() : "";
    }
}
```

The compact constructor runs before the fields are set; here we validate `age` and normalize `name`. No need to repeat `this.name = name; this.age = age;`.

### Using the runnable example

The code in `"workbench/records-example/Main.java"` uses a `Person` record like the one above and prints accessors, `toString`, and `equals` with an equivalent instance.

---

## Commands

**Prerequisites:** Java 16 or later. Check with `java -version`.

From the experiment directory `"workbench/records-example/"`:

```bash
javac Main.java
java Main
```

Expected output: one line with name and age, the record’s `toString()`, and `true` for equality with another `Person` with the same components.

---

## Pitfalls / Caveats / Notes

- **Immutability** — Component fields are final. You cannot add mutable state or reassign components after construction. For mutable data, use a class.
- **No inheritance** — Records are final. You cannot extend a record or extend from a record (except for the implicit `extends Record`). Use composition or interfaces instead.
- **Accessor names** — Accessors are `name()` and `age()`, not `getName()`/`getAge()`. Some tools or frameworks may expect getter-style names; check compatibility.
- **Version** — Records require **Java 16+** (language feature). They are final and part of the language since Java 16 (JEP 395).

---

## References

- **This experiment:** `"workbench/records-example/Main.java"`
- **Workbench agent:** `"agents/workbench.md"`
- **Document rules:** `"agents/documents.md"`
- **Workbench overview:** `"workbench/README.md"`
- **JEP 395 (Records):** [openjdk.org/jeps/395](https://openjdk.org/jeps/395)
- **Java Language Guide — Records:** [docs.oracle.com/en/java/javase/21/language/records.html](https://docs.oracle.com/en/java/javase/21/language/records.html)
