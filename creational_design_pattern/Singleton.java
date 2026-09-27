package creational_design_pattern;
class Logger {
    private static Logger instance;

    private Logger() {
        // private constructor to prevent instantiation
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
}


/*
# Singleton Design Pattern

### Goal

> **Allow only ONE object of a class to be created.**

---

## Basic Singleton Code

```java
class Logger {

    // One shared reference to the Logger object
    private static Logger instance;

    // Prevent anyone from doing: new Logger()
    private Logger() {
    }

    // Give everyone access to the same Logger object
    public static Logger getInstance() {

        // If the object does not exist, create it
        if (instance == null) {
            instance = new Logger();
        }

        // Return the existing object
        return instance;
    }
}
```

---

## Understand Every Part

### 1. `private static Logger instance;`

```java
private static Logger instance;
```

* `private` → only this class can access it
* `static` → one shared variable belongs to the class
* `Logger` → type, derived from our `Logger` class
* `instance` → variable name

Initially:

```text
instance → null
```

**Important:** This does NOT create a Logger object.

---

### 2. `private Logger()`

```java
private Logger() {
}
```

This is the **constructor**.

Normally we could do:

```java
Logger logger = new Logger();
```

But because the constructor is `private`, code outside the class cannot do that.

So:

```text
private constructor
       ↓
Nobody can directly create Logger objects
```

---

### 3. `public static Logger getInstance()`

```java
public static Logger getInstance()
```

This is a **method**, NOT a constructor.

* `public` → can be called from outside
* `static` → can call it without creating a Logger object
* `Logger` → return type
* `getInstance()` → method name

Therefore:

```java
Logger logger = Logger.getInstance();
```

---

### 4. `if (instance == null)`

```java
if (instance == null) {
```

Ask:

> **"Have we already created the Logger object?"**

If:

```text
instance == null
       ↓
No object exists
```

then:

```java
instance = new Logger();
```

creates the object.

---

### 5. `return instance`

```java
return instance;
```

Give the caller the Logger object.

---

# First Call

```java
Logger logger1 = Logger.getInstance();
```

Initially:

```text
instance → null
```

So:

```text
instance == null
       ↓
      TRUE
       ↓
new Logger()
       ↓
instance ─────→ Logger Object #1
```

Then:

```text
logger1 ──────→ Logger Object #1
```

---

# Second Call

```java
Logger logger2 = Logger.getInstance();
```

Now:

```text
instance ─────→ Logger Object #1
```

Therefore:

```text
instance == null
       ↓
      FALSE
       ↓
Don't create another object
       ↓
return instance
```

So:

```text
logger1 ──────┐
              ↓
        Logger Object #1
              ↑
logger2 ──────┘
```

Both variables point to the **same object**.

---

# What Singleton Prevents

Without Singleton:

```text
logger1 ──→ Logger object #1

logger2 ──→ Logger object #2

logger3 ──→ Logger object #3
```

With Singleton:

```text
logger1 ──┐
logger2 ──┼──→ Logger object #1
logger3 ──┘
```

**ONE object. Multiple references.**

---

# The Mental Recipe

Don't memorize the code line-by-line.

Remember:

```text
I want ONLY ONE object
          ↓
Prevent direct object creation
          ↓
private constructor
          ↓
Need somewhere to store that ONE object
          ↓
static instance
          ↓
Need a way to access it
          ↓
static getInstance()
          ↓
Does object exist?
     ↙           ↘
   NO             YES
    ↓              ↓
new Logger()    use existing
     \            /
      \          /
       return instance
```

---

# ⚠️ Important: Thread-Safety Problem

The basic Singleton above has a problem when **multiple threads call `getInstance()` at exactly the same time**.

```text
Thread 1                    Thread 2

if(instance == null)        if(instance == null)
       ↓                           ↓
     TRUE                        TRUE
       ↓                           ↓
new Logger()                  new Logger()
       ↓                           ↓
Logger Object #1             Logger Object #2
```

Now:

```text
Thread 1 → Logger Object #1
Thread 2 → Logger Object #2
```

❌ Two objects were created.

Therefore, the basic implementation is **not thread-safe**.

This is the next level of Singleton:

```text
Basic Singleton
      ↓
Understand the pattern
      ↓
Thread-safe Singleton
      ↓
Double-checked locking
      ↓
volatile
```

For now, the most important mental model is:

> **Private constructor + static instance + getInstance() + create only if null = Singleton.**

*/