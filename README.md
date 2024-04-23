# COP4331 Final Project - Command Line Interface (CLI) for Basic Operations

This Command Line Interface (CLI) application provides functionalities for performing basic mathematical operations, date and time parsing, and other calculations. The application is implemented in Java and follows the Object-Oriented Programming (OOP) paradigm.

## Structure

The project consists of several directories:

Within the directories the main components of the projects are located within our Scenarios.java, and Main.java

ScenarioTests.java contains all of our unit tests and use cases

## Functions

### Parsing Commands

The `Scenarios` class provides a `parse` method that accepts a command string and delegates the execution to specific methods based on the command base. Each operation (e.g., addition, subtraction) is implemented as a separate method within the `Scenarios` class.

### Mathematical Operations

- **Addition (`add`)**: Adds two integers.
- **Subtraction (`sub`)**: Subtracts one number from another.
- **Square Root (`sqrt`)**: Calculates the square root of a non-negative integer.
- **Multiplication (`mul`)**: Multiplies two numbers.
- **Division (`div`)**: Divides one number by another.
- **Power (`pow`)**: Raises a base number to a given exponent.
- **Factorial (`fact`)**: Calculates the factorial of a non-negative integer.
- **Logarithm (`log`)**: Calculates the natural logarithm of a positive number.

### Date and Time Operations

- **Date Parsing (`date`)**: Parses a string representing a date in the format `yyyy-MM-dd`.
- **Time Parsing (`time`)**: Parses a string representing a time in the format `HH:mm:ss`.

## Documentation

Each method in the `Scenarios` class is documented with Javadoc comments to provide information about its purpose, parameters, return values, and possible exceptions.

## Error Handling

The application includes comprehensive error handling to handle invalid inputs, missing arguments, and other exceptional cases. Each method throws `IllegalArgumentException` with informative error messages when encountering invalid inputs.

## How to Use

To use the CLI application, follow these steps:

1. Compile the Java files using `javac`.
2. Run the application with `java` followed by the main class (`oop.project.cli.Scenarios`).
3. Enter commands in the CLI interface to perform various operations.

Example commands:
```
add 5 3
sub --left 10 --right 4
sqrt 25
date 2024-04-22
time 13:45:30
calc mul
```

## Contributors

- Adrien Chen, Guhan Gnanam, Patrick Quinlan, Vedant Shringari


