<div align="center">
  <h1>Javasm & JAL</h1>
  <p>If you can't understand it, you don't belong here - Mike Gancarz</p>
  <img src="https://github.com/PeyaPeyaPeyang/Javasm/blob/main/docs/logo.svg?raw=true" alt="Javasm Logo" width="200">
</div>

Javasm is the IntelliJ IDEA plugin for the **[JAL \(Java Assembly Language)](https://github.com/PeyaPeyaPeyang/LangJAL)** — a custom-designed assembly language for the Java Virtual Machine.  
It makes exploring the internals of the JVM not just possible, but genuinely fun.

---

## 🚀 What is JAL?

JAL is a fresh take on Java assembly — a spiritual successor to projects like **Jasmin**, but with modern features and design.

Unlike Jasmin, which stopped development around 2022 and only supports outdated Java versions, **JAL supports modern JVM features**, including automatic generation of **StackMapFrames**, which are mandatory from Java 1.6 and up. Jasmin users may remember `VerifyError`s haunting their dreams — JAL eliminates them.

### Why JAL is cool:

- **Named local variables**  
  Instead of remembering slot numbers, you can name your variables:
  ```
  istore 0 [->example]  
  iload example
  ```
- **Structured exception handling with labels**  
  Try-catch-finally blocks are declared with jump labels:
  ```
  tryStart: [~tryEnd, java/lang/Exception: catchStart, java/lang/Error: catchStart2 ->finallyStart]  
  tryEnd:  
    
  catchStart:  
  catchStart2:  
  finallyStart:
   ```

- **Readable member references**  
  Method and field calls separate class and member names for clarity:
  ```
  invokevirtual java/io/PrintStream->println(Ljava/lang/String;)V
  ```

### Example: HelloWorld

```java
public class HelloWorld {
  public static main([Ljava/lang/String;)V {
    // Print "Hello, World!"
    getstatic java/lang/System->out:Ljava/io/PrintStream;
    ldc "Hello, World!"
    invokevirtual java/io/PrintStream->println(Ljava/lang/String;)V
    
    // Return from main
    return
  }
}
```

### Example2: FizzBuzz

```java
public class FizzBuzz {
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
    iinc 1 1
    goto LoopStart

  LoopEnd:
  // return;
    return
  }
}
```

---

## 🧠 StackMapFrame? Automatically Done.

One of the biggest pain points in JVM bytecode writing is StackMapFrame management.  
JAL's compiler **calculates and inserts StackMapFrames automatically** — no more manual frame declaration, no more hair-pulling `VerifyError`s.

---

## 🔌 Javasm IntelliJ Plugin Features

Javasm supercharges your JAL development inside IntelliJ IDEA:

- ✅ **Instruction name completion**  
  Start typing and get autocompletion for all instructions.

- 📄 **Hover documentation**  
  Hover over instructions to see inline documentation.

- 🌀 **Label navigation**  
  Ctrl+Click a label to jump to its declaration.

- 🐞 **Debugger integration**  
  Full integration with IntelliJ's standard JVM debugger (JDWP).  
  Breakpoints, step-over, step-into — all supported.

- 📊 **Frame and Stack Viewer**   
  A custom tool window that shows:
  - Stack state at the selected instruction
  - Local variable states
  - Visualises the stack during live debugging sessions

---

## 📦 Installation

<a href="https://plugins.jetbrains.com/plugin/27944-javasm/"><img src=".github/readme/marketplace.svg" alt="Javasm on JetBrains Marketplace" width="200"></a>

This plugin contains JAL implementation V1.2.2!

No manual build needed — just plug and play.

---

## 📄 Licence

MIT Licence. See the [LICENCE](./LICENCE) file for details.

---

❤️ A project by someone who enjoys poking around the JVM bytecode guts.
