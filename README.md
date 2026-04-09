# Sudoku Puzzle Generator (APCSA Project)

Name: Vincent Lin


**Description**

This program essentially generates a Sudoku board solution, and then randomly removes numbers from the solution to create an interactive Sudoku game where users can fill in the missing numbers.

**How To Run The Program**

The code was written in CodeHS Java Sandbox, so to run the first part of the program (generating the solution), change the file named "SudokuGenerator.java" to the main file by clicking the dropdown next to the file name on the left-hand panel, and then click "Run". To run the second part of the program (generating the game), change the file "SudokuGame.java" to the main file and then click "Run". The program will first ask you to select a difficulty by typing 1 (Easy), 2 (Medium), or 3 (Hard). Then the Sudoku board will be generated and start guessing! 

NOTE: When inputting the row or column of your number, make sure to start counting from 0 instead of 1. For example, if your number is located in row 4, type 3 because that represents the index.

**Summary**

For generating the Sudoku board, I implemented a recursive, backtracking algorithm, where the program would test a random number from 1-9, verify if it has any constraints (either row, column, or the 3x3 box), and place it in the cell. If the number is invalid, it backtracks and tries out a different number. The program will keep running until all cells on the Sudoku Board are filled, and then return the completed Sudoku. For generating the Sudoku game, the program basically randomly removes a number of values from the generated board based on the difficulty the user selects, and then creates the puzzle.

**Files Included**

- Design Document 
- Java Source Code
- README
- Video
