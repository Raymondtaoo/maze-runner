# Maze Runner - SFWRENG 2AA4 Assignment A1

## Introduction

Welcome to my implementation of the Maze Runner project for the SFWRENG 2AA4 course at McMaster University. This project is part of the Software Design I - Introduction to Software Development curriculum, and it showcases the skills I've learned in algorithm design and Java programming.

## Business Logic Specification

The Maze Runner program is designed to explore a maze and find a path from an entry point to an exit. Key features include:

- Maze representation through text files, with `#` for walls and `␣` (empty space) for passages.
- Ability to handle mazes of various complexities and layouts.
- Generation of a sequence of instructions (`F`, `R`, `L`) to navigate the maze.
- Support for both canonical and factorized path representations.
- Functionality to verify the correctness of a provided path.

## How to Run

To build and run the Maze Runner, use Maven as follows:

```bash
mvn -q clean package
java -jar target/mazerunner.jar [OPTIONS]
```

## Command Line Arguments

- `-i MAZE_FILE`: Specify the maze file to use.
- `-p PATH_SEQUENCE`: Activate path verification mode.
- `-m {tremaux, righthand}`: Choose the path computation method (default: righthand).

## Examples
```
java -jar target/mazerunner.jar -i ./examples/straight.maz.txt
java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 4F
java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 3F
```

## Implementation Details

This project is implemented in Java, utilizing principles of object-oriented programming and design patterns. Key components include:

- `Main`: The entry point of the application, handling command-line arguments and coordinating the maze exploration process.
- `MazeReader`: Responsible for reading the maze from a file and converting it into a usable data structure.
- `PathValidator`: Checks if a given path is valid for the specified maze.
- `RightHandFinder`: Implements the right-hand rule algorithm for maze navigation.
- `DirectionUtility`: Provides utility functions for direction manipulation.

## Dependencies

- Apache Commons CLI for command-line argument parsing.
- Log4J for logging and debugging.

## Acknowledgements

Special thanks to Dr. Sébastien Mosser and the faculty of McMaster University's Department of Computing and Software for their guidance and support in this project.