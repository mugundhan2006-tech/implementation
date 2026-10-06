# Library Book Inventory

A menu-driven Java command-line application for managing an in-memory collection of books. It supports adding, listing, updating, and deleting books. Each book is identified by its ISBN, and duplicate ISBNs are rejected.

## Requirements

- JDK 8 or newer

## Compile

Open a terminal in the directory containing the source files and run:

```powershell
javac Book.java LibraryInventory.java LibraryApplication.java
```

## Run

```powershell
java LibraryApplication
```

The inventory is held in a `LinkedHashMap` while the program runs. Data is not saved after the application exits.

## Files

- `Book.java` defines the immutable book entity and validates its properties.
- `LibraryInventory.java` implements the ISBN-keyed in-memory CRUD operations.
- `LibraryApplication.java` provides the menu, input validation, and user-facing error handling.