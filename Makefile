# Makefile for Sudoku Solver without JUnit

# Variables
JFLAGS = -g
JC = javac
JVM = java
SRC = Position.java Tree.java AbstractTree.java GameBoard.java IntegerBoard.java GameSolver.java SudokuSolver.java SudokuApp.java
MAIN = SudokuApp

# Default target
all: compile

# Compile all .java files
compile:
	$(JC) $(SRC)

# Run the test case
run:
	$(JVM) $(MAIN)

# Clean up .class files
clean:
	rm -f *.class
