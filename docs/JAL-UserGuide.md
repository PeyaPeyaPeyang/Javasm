# JAL Usage Guide

Welcome to **JAL (Java Assembly Language)** — a powerful and expressive assembly-like language designed specifically for JVM bytecode manipulation and development.  
This document provides a detailed user-oriented explanation of JAL’s syntax and usage so you can write and understand JAL source files with confidence.

---

## Overview

JAL is an assembly language tailored for the Java Virtual Machine (JVM). It exposes JVM bytecode instructions in a human-readable, structured format while adding several usability improvements to make low-level JVM programming more accessible.

The language lets you define classes, interfaces, fields, methods, and the instructions inside those methods — all using syntax closely related to JVM bytecode, but enhanced for clarity and ease of use.

---

## Key Concepts

### Class and Interface Declaration

A JAL source file may define one or more classes or interfaces. The declaration syntax resembles Java but is simplified and specialized for bytecode-level details:

```
class MyClass (major_version=55, minor_version=0) {
// class body here
}
```

- `class` or `interface` keywords start declarations.
- Version numbers specify the target JVM version.
- Classes may extend superclasses and implement interfaces with metadata.

### Class Metadata

JAL allows embedding detailed metadata about classes directly within the class declaration. 
These metadata properties control fundamental class attributes relevant to the JVM runtime and class file structure:

- **`major_version`** and **`minor_version`**  
  Specify the class file version, which determines the minimum JVM version required to load this class. 
  For example, `major_version=55` targets Java 11.
- **`super_class`**  
  Defines the fully qualified superclass that this class extends. If omitted, the default superclass is `java/lang/Object`.  
  Example: `super_class=java/lang/Number`
- **`interfaces`**  
  Lists one or more fully qualified interfaces that the class implements. 
  Multiple interfaces are comma-separated: `interfaces=java/io/Serializable,java/lang/Cloneable`

These metadata are declared inside parentheses immediately after the class name and separated by commas if multiple:

```
class MyClass (
  major_version=55,
  minor_version=0,
  super_class=java/lang/Object,
  interfaces=java/io/Serializable,java/lang/Cloneable
) {
// class body
}
```

Using class metadata ensures that the generated class files conform to 
desired JVM specifications and inheritances without additional boilerplate code inside the class body.

### Access Modifiers and Attributes

You can specify JVM access flags such as `public`, `private`, `protected`, `static`, `final`, `abstract`, etc., on classes, methods, and fields:

```
public static final class MyClass { ... }
private volatile int myField;
protected synchronized void myMethod() { ... }
```

These modifiers map directly to JVM access flags, controlling visibility and behavior.

### Fields

Fields are declared by specifying modifiers, a name, a type descriptor, and optionally an initial constant value:

```
private static final myField: I = 123;
```

Here, `I` is the JVM type descriptor for `int`.

### Methods and Method Signatures

Methods are defined with modifiers, a name, and a method descriptor indicating argument types and return type:

```
public static main([Ljava/lang/String;)V {
// instructions here
}
```

- Method names can be normal identifiers or special JVM method names like `<init>` (constructor) and `<clinit>` (static initializer).
- Method descriptors follow JVM format, e.g., `([Ljava/lang/String;)V` means method takes a String array and returns void.

---

## Instructions and Labels

### Instructions

Inside method bodies, you write JVM bytecode instructions line by line, each corresponding to a JVM opcode. JAL supports the full set of JVM instructions as tokens.

Example:

```
getstatic java/lang/System->out:Ljava/io/PrintStream;
ldc "Hello, World!"
invokevirtual java/io/PrintStream->println(Ljava/lang/String;)V
return
```

- Instructions may take arguments: literals, references to fields/methods/classes, labels, or numeric constants.
- References to members follow the syntax: `<owner_class>/<field_or_method_name>:<type_descriptor>` or `<owner_class>-><method_name><method_descriptor>`.
- Literal strings and numeric constants can be used as instruction operands (e.g., `ldc "string"` or `bipush 10`).

### Labels and Control Flow

You can declare labels to mark positions in code for jumps, branches, exception handlers, etc.:

```
loopStart:
  iload_0
  ifeq loopEnd
  iinc 0 1
  goto loopStart
loopEnd:
```

Labels end with a colon (`:`) and can be referenced by jump instructions.

### Try-Catch-Finally Blocks

JAL allows declaration of exception handling regions using label-based syntax inside method bodies:

```
tryStart: [~tryEnd, java/lang/Exception: catchStart, ->finallyStart]
tryEnd:
catchStart:
finallyStart:
```

- This declares a try block starting at `tryStart` ending at `tryEnd`.
- Catches `java/lang/Exception` at `catchStart`.
- Finally block starts at `finallyStart`.
- This syntax clearly maps to JVM exception tables but is much more readable.

---

## Type Descriptors

JAL uses JVM standard type descriptors to specify types:

| Descriptor      | Type        | Example Usage        |
|-----------------|-------------|----------------------|
| `B`             | byte        | `myField: B`         |
| `C`             | char        | `charVal: C`         |
| `D`             | double      | `doubleVal: D`       |
| `F`             | float       | `floatVal: F`        |
| `I`             | int         | `intVal: I`          |
| `J`             | long        | `longVal: J`         |
| `S`             | short       | `shortVal: S`        |
| `Z`             | boolean     | `boolVal: Z`         |
| `V`             | void        | Used only in methods |
| `L<classname>;` | Object type | `Ljava/lang/String;` |

Arrays are indicated by preceding with `[`, e.g., `[I` for int array.

---

## Method Descriptors

Method descriptors specify argument types and return type, enclosed in parentheses, e.g.:

```
([Ljava/lang/String;)V
()Ljava/lang/String;
([IIDLjava/lang/Object;[Ljava/lang/String;)[Ljava/lang/String;
```


Means method takes an array of Strings and returns void.

---

## Member References

In JAL, when you refer to fields or methods inside instructions, 
you must specify them explicitly to avoid ambiguity and to directly map JVM semantics.

### General Syntax

#### **Field Reference:** `owner_class/field_name:type_descriptor`

- `owner_class` is the fully qualified JVM internal class name (using `/` as separator).
- `field_name` is the field identifier.
- `type_descriptor` is the JVM type descriptor of the field.

**Example:**
```
java/lang/System/out:Ljava/io/PrintStream;
```

This refers to the `out` static field in the `java.lang.System` class, which is of type `java.io.PrintStream`.

#### **Method Reference:** `owner_class->method_name(method_descriptor)`

- `owner_class` is the fully qualified JVM internal class name.
- `method_name` is the method identifier (can be special names like `<init>` or `<clinit>`).
- `method_descriptor` follows JVM specification describing argument and return types.

**Example:**
```
java/io/PrintStream->println(Ljava/lang/String;)V
```

Refers to the `println` method in `java.io.PrintStream` that takes a `String` and returns `void`.

---

### Omitting OwnerClass (When Referring to Current Class)

When the field or method belongs to the **current class** (the one being defined), you **can omit** the `owner_class` part for convenience. This allows writing cleaner and more concise code without losing clarity.

- **Field Reference in Current Class:** `fieldName:type_descriptor`  
  Instead of writing `my/package/MyClass/myField:I`, you can just write `myField:I`.
- **Method Reference in Current Class:** `methodName(method_descriptor)`  
  Instead of `my/package/MyClass->myMethod()V`, you can write `myMethod()V`.

This shorthand is especially useful when invoking or accessing your own class’s members, reducing noise while keeping code straightforward.

---

### Examples

```
// Accessing a field in another class
getstatic java/lang/System/out:Ljava/io/PrintStream;

// Invoking a method in another class
invokevirtual java/io/PrintStream->println(Ljava/lang/String;)V;

// Accessing a field in the current class (owner class omitted)
getfield myField:I;

// Invoking a method in the current class (owner class omitted)
invokevirtual ->helperMethod()V;
```

---

### Notes

- The omitted `owner_class` always refers to the **class currently being compiled**.
- Omitting owner_class is **only allowed** when referencing members defined in the current class.
- When referencing external classes, the full qualified `owner_class` must be provided to avoid ambiguity.
- This design balances explicitness and brevity, helping you write cleaner JAL code.

---

## Local Variables and Named Slots

JAL supports naming local variables, not just numeric slots, improving readability:

```
istore 0 [->example]
iload example
```

Here, slot 0 is named `example`, making code easier to follow.

All local variables can be named but it is not mandatory.
You can still use numeric slots directly if preferred.

---

## Advanced Features

### StackMapFrame Auto-Calculation

JAL compiler automatically generates StackMapFrames required by JVM verification, eliminating the tedious manual management of stack frames.

### Try-Catch-Finally Directives

Structured try-catch-finally exception handling declarations via labels make writing exception handling straightforward and readable.

---

## Writing a JAL Source File — Summary

- Declare a class or interface with optional versioning and inheritance metadata.
- Define fields and methods with access modifiers.
- Write method bodies as sequences of instructions with optional labels.
- Use JVM standard type descriptors for fields, method arguments, and return types.
- Use labels to structure control flow and exception handling.
- Reference JVM classes, methods, and fields using explicit owner/member/type syntax.
- Leverage named locals for clearer code.
- Use exception handler blocks via label directives.
- Let the compiler handle low-level JVM StackMapFrames automatically.

---

## Example Snippet

```
public class FizzBuzz (
  major_version=55,
  minor_version=0) {

  public static main([Ljava/lang/String;)V {
  // int i = 1;
    iconst_1
    istore_1  // Store the value of i in local variable 1

  LoopStart:
  // if (i >= 101) goto LoopEnd;
    iload_1
    bipush 101
    if_icmpge LoopEnd

  // if (i % 15 = 0)
    iload_1
    bipush 15
    irem
    ifne NotFizzBuzz

  // System.out.println("FizzBuzz");
    getstatic java/lang/System->out:Ljava/io/PrintStream;
    ldc "FizzBuzz"
    invokevirtual java/io/PrintStream->println(Ljava/lang/String;)V
    goto LoopIncrement

  NotFizzBuzz:
  // else if (i % 3 == 0)
    iload_1
    iconst_3
    irem
    ifne NotFizz

  // System.out.println("Fizz");
    getstatic java/lang/System->out:Ljava/io/PrintStream;
    ldc "Fizz"
    invokevirtual java/io/PrintStream->println(Ljava/lang/String;)V
    goto LoopIncrement

  NotFizz:
  // else if (i % 5 == 0)
    iload_1
    iconst_5
    irem
    ifne PrintNumber

  // System.out.println("Buzz");
    getstatic java/lang/System->out:Ljava/io/PrintStream;
    ldc "Buzz"
    invokevirtual java/io/PrintStream->println(Ljava/lang/String;)V
    goto LoopIncrement

  PrintNumber:
    getstatic java/lang/System->out:Ljava/io/PrintStream;
    iload_1
    invokevirtual java/io/PrintStream->println(I)V

  LoopIncrement:
  // i++
    iinc
    goto LoopStart

  LoopEnd:
  // return;
    return
  }
}
```

You can find more examples in the [JAL example files](examples) directory.

---

## Conclusion

JAL brings JVM bytecode development into a clearer, more maintainable syntax that bridges assembly-level control with user-friendly features like named locals and structured exception directives.

This documentation covers core syntax and concepts to get started writing JAL code for JVM-level programming and experimentation.

Happy bytecoding!

