# **The Tenth Door**

A Java-based, minimalist GUI open-world puzzle game built around ten progressively chaotic rooms. Each room introduces a distinct problem style, escalating in complexity and pace. The core loop: explore, solve, survive, unlock the next door.

## **Features**

* Lightweight Java GUI (Swing/JavaFX depending on your implementation)
* Ten self-contained levels with unique logic
* Puzzle, timing, memory, and deception mechanics
* Structured progression from simple riddles to ultra-hard glitch-style challenges
* Compact codebase suitable for learning, extension, or modding

## **Level Overview**

| Room   | Difficulty | Core Mechanic                                                          |
| ------ | ---------- | ---------------------------------------------------------------------- |
| **1**  | Easy       | Key hidden under one of two items; short riddles.                      |
| **2**  | Easy       | Key under one of three items; fake countdown and misdirection riddles. |
| **3**  | Easy       | In-game alphabetical crossword puzzle.                                 |
| **4**  | Hard       | Zombie encounter with a strict timer.                                  |
| **5**  | Hard       | Memory-driven maze navigation.                                         |
| **6**  | Medium     | “Old Man – O Old Man” logic sequence (dialogue-based puzzle).          |
| **7**  | Medium     | Continuation of the Old Man mechanic with higher complexity.           |
| **8**  | Hard       | Final Old Man variant with stricter conditions.                        |
| **9**  | Easy       | A deceptive “fake room” designed to mislead.                           |
| **10** | Ultra Hard | Credits-room glitch puzzle; intended as the endgame skill check.       |

## **Requirements**

* Java 17+
* Standard JRE 
* Javafx

## **Running the Game**

Compile and launch from the project root:

```bash
javac -d out src/**/*.java
java -cp out Main
```

(or run through your IDE)

## **Project Structure**

```
/src
  /main          # Core loop, game state, event flow, room transitions
  /tile          # GUI tile system and rendering components
  /entity        # Player logic, behavior, and movement
  /res           # Images, audio, and other static resources
  /object        # Obstacles, interactables, room objects
/out             # Compiled classes

```

## **Planned Improvements**

* Asset upgrades
* Additional events between rooms
* Optional difficulty scaling
* Save system and checkpoints
