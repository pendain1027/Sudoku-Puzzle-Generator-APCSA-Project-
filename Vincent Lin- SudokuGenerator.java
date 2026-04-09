SudokuGame.java:
---------------
/*
NOTE: This code is for Part II of the project. 
*/
import java.util.*;
public class SudokuGame {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        SudokuGenerator.solve(SudokuGenerator.board, 0, 0); //generate complete sudoku board
        int[][] solution = copyBoard(SudokuGenerator.board); //store the solution for comparison
        int removed = chooseDifficulty();
        int[][] puzzle = copyBoard(solution); //create the puzzle 
        removeNumbers(puzzle, removed); 
        playGame(puzzle, solution);
    }
    
    public static int chooseDifficulty(){ //choose the difficulty
        System.out.println("Welcome to your Sudoku game!" + "\n" + "Choose the difficulty:");
        System.out.println("Easy Mode: Type 1 (40 numbers removed)");
        System.out.println("Medium Mode: Type 2 (50 numbers removed)");
        System.out.println("Hard Mode: Type 3 (60 numbers removed)");
        int choice = sc.nextInt();
        if (choice == 1){
            return 40;
        }
        if (choice == 2){
            return 50;
        }
        if (choice == 3){
            return 60;
        }
        System.out.println("Invalid choice. Defaulting to easy mode");
        return 40;
    }
    public static void playGame(int[][] puzzle, int [][] solution){
        int guesses = 0;
        while(true){
            System.out.println();
            printBoard(puzzle); 
            System.out.println("\n" + "NOTE: 0 corresponds to the 1st row/col, not 1!");
            System.out.println("Please enter the row (0-8) of your number, or -1 to quit: "); 
            int row = sc.nextInt();
            System.out.println("Please enter the column (0-8) of your number, or -1 to quit: "); 
            int col = sc.nextInt();
            System.out.println("Please enter your number (1-9), or -1 to quit: "); 
            int num = sc.nextInt();
            if (row == -1 || col == -1 || num == -1){
                System.out.println("Game Ended.");
                break;
            }
            //if user input is invalid
            if (row < 0 || row > 8 || col < 0 || col > 8 || num < 1 || num > 9) {
                System.out.println("Invalid input. Try again");
                continue;
            }
            //if user tries to change preexisting cells
            if (puzzle[row][col] != 0){
                System.out.println("You can't change this cell. Try a different slot");
                continue;
            }
            //check if user's guess is correct
            if (solution[row][col] == num){
                puzzle[row][col] = num;
                System.out.println("Correct! :)");
                guesses++;
            }
            else{
                System.out.println("Wrong! Try Again");
                guesses++;
            }
            //if user finishes the sudoku
            if(isComplete(puzzle)){
                printBoard(puzzle);
                System.out.println("Yippee! You solved the puzzle with " + guesses + " tries.");
                break;
            }
        }
    }

    //copy the original solution
    public static int[][] copyBoard(int[][] original){ 
        int[][] copy = new int[9][9];
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }
    //remove random numbers from the board
    public static void removeNumbers(int[][] board, int count){
        int removed = 0;
        while (removed < count){
            int row = (int) (Math.random() * 9);
            int col = (int) (Math.random() * 9);
            if (board[row][col] != 0){
                board[row][col] = 0;
                removed++;
            }
        }
    }
    //check if the board is done
    public static boolean isComplete(int[][] board){
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if(board[i][j] == 0){
                    return false; //not done yet
                }
            }
        }
        return true;
    }
    //print the board with blanks instead of 0
    public static void printBoard(int[][] board){
        for (int row=0; row<9; row++){
            if (row != 0 && row % 3 == 0){
                System.out.println("---------------------");
            }
            for (int col=0; col<9; col++){
                if (col != 0 && col % 3 == 0){
                    System.out.print("| ");
                }
                if (board[row][col] == 0) {
                    System.out.print("_ ");
                }
                else{
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
        }
    }
}

SudokuGenerator.java:
--------------------
/*
NOTE: This code is for Part I of the project. 
*/
import java.util.*;
public class SudokuGenerator
{
    public static int[][] board = new int[9][9]; //create sudoku board
    public static void main(String[] args)
    {
        //fill sudoku board with 0s (empty cells)
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                board[i][j] = 0;
            }
        }
        solve(board, 0, 0); //start inputting from the 1st cell
        printBoard(board); //print the finished sudoku board
    }
    
    public static boolean solve(int[][] board, int row, int col){
        if (row == 9){ //if row 9 is reached, the board is fully filled
            return true; 
        }
        
        if (col == 9){ //go to next row if the end of the row is reached
            return solve(board, row+1, 0); 
        }
        
        if (board[row][col] != 0){ //if current cell is already filled, move to next column
            return solve(board, row, col+1);
        }
        
        int[] nums = getShuffledNumbers(); //shuffle numbers to create different boards each time
        for (int num : nums){
            if (isValid(board, row, col, num)){
                board[row][col] = num;
                if (solve(board, row, col+1)){
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }
    
    public static boolean isValid(int[][] board, int row, int col, int num){
    //check if the row, column, and box are all still valid  
        for (int c = 0; c < 9; c++){ //check rows
            if (board[row][c] == num){
                return false;
            }
        }
        
        for (int r = 0; r < 9; r++){ //check columns
            if (board[r][col] == num){
                return false;
            }
        }
        
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r <= startRow+2; r++){ //check the 3x3 box
            for (int c = startCol; c <= startCol+2; c++){
                if (board[r][c] == num){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void printBoard(int[][] board){
        for (int row = 0; row < 9; row++){ //adding borders
            if (row != 0 && row % 3 == 0){
                System.out.println("---------------------");
            }
            for (int col = 0; col < 9; col++){ //adding borders
                if (col != 0 && col % 3 == 0){
                    System.out.print("| ");
                }
                System.out.print(board[row][col] + " "); //fill in numbers
            }
            System.out.println();
        }
    }
    
    public static int[] getShuffledNumbers(){
        int[] nums = {1,2,3,4,5,6,7,8,9};
        for (int i = nums.length-1; i>0; i--){ //randomly swap the numbers
            int rand = (int) (Math.random() * (i+1));
            int temp = nums[i];
            nums[i] = nums[rand];
            nums[rand] = temp;
        }
        return nums;
    }
}

