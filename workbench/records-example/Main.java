/**
 * Java Records workbench — runnable example.
 *
 * Demonstrates:
 * - Record declaration with components (name, age)
 * - Compact constructor for validation and normalization
 * - Generated accessors (name(), age()), toString(), equals()
 *
 * See "workbench/records-example/README.md" for full documentation.
 */
public class Main {

    // Record: components become private final fields + public accessors.
    // Compact constructor runs before field assignment; use for validation/normalization.
    record Person(String name, int age) {
        public Person {
            if (age < 0) throw new IllegalArgumentException("age must be >= 0");
            name = name != null ? name.strip() : "";
        }
    }

    public static void main(String[] args) {
        var alice = new Person("Alice", 30);
        var bob = new Person("Bob", 25);

        // Accessors: name() and age() — no getX() boilerplate.
        System.out.println(alice.name() + " is " + alice.age());

        // Generated toString() and equals() based on components.
        System.out.println(alice);
        System.out.println("Same as copy: " + alice.equals(new Person("Alice", 30)));
    }
}
