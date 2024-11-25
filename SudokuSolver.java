/**
 * SudokuSolver is a class implementing GameSolver it contains methods resolving a given sudoku board
 * * of n by n size
 *
 * Based on Goodrich, Tamassia, Goldwasser and the course IFT2015 code
 *
 * @Joseph Finan et Lara Simone Suarez Lopez
 * @version     1.0
 * @since       1.0
 */


public class SudokuSolver implements GameSolver {
    private final IntegerBoard board;        // Original unsolved board
    private IntegerBoard solution;           // Solution board
    private final LinkedGeneralTree<IntegerBoard> solutionTree;//Tree of possible solutions
    int size;//size of the board


    // Constructor initializes the board and the solution tree
    public SudokuSolver(GameBoard<Integer> board) {
        if (!(board instanceof IntegerBoard)) {
            throw new IllegalArgumentException("Board must be an instance of IntegerBoard.");
        }
        this.board = (IntegerBoard) board;
        this.size = board.getWidth();
        this.solution = this.board;// Initialize solution
        this.solutionTree = new LinkedGeneralTree<>(this.solution);// Tree root as initial board
    }

    // Solves the Sudoku puzzle
    @Override
    public boolean solve() {
        // Start solving from the root node of the solution tree
        boolean solved = solveBoard(solutionTree.root());
        if (!solved) {
            solution = null;  // If unsolvable, reset solution to null
        }
        return solved;
    }

    // Print the solution if the puzzle is solved
    @Override
    public void printSolution() {
        if (solution != null) {
            System.out.println("Sudoku Solved:");
            solution.display();
        } else {
            System.out.println("No solution exists.");
        }
    }
    //Method to solve the board with a tree, identifying the most restricted entry and adding as a child the sudoku
    // Board with the entry filled by one of the possibles options and proceeding by recursion
    private boolean solveBoard(Position<IntegerBoard> currentNode) {

        IntegerBoard currentBoard = currentNode.getElement();

        int[] rowCol = findMostRestrictedEntry(currentBoard);

        if (rowCol[0] != -1 && rowCol[1] !=-1) {
            //iterates over numbers 1 to size
            for (int num = 1; num <= size; num++) {
                if (isValidPlacement(currentBoard, rowCol[0], rowCol[1], num)) {
                    currentBoard.setCell(rowCol[0], rowCol[1], num);  // Place number
                    //creates child node
                    Position<IntegerBoard> childNode = solutionTree.addNode(currentBoard, currentNode);
                    // Recursively attempt to solve
                    if (solveBoard(childNode)) {
                        return true;
                    } else {
                        solutionTree.remove(childNode);//remove the last child added
                    }
                    currentBoard.setCell(rowCol[0], rowCol[1], 0);  // Reset cell if no solution found
                }
            }
            return false;  // Return false if no valid number can be placed here


        }
        // Solution found; set this board state as the solution
        solution = currentBoard;
        return true;

    }

    // Checks if placing a number in the cell does not violate Sudoku rules
    public boolean isValidPlacement(IntegerBoard currentBoard, int row, int col, Integer value) {
        // Check the row
        for (int i = 0; i < size; i++) {
            if (currentBoard.getCell(row, i) == value) {
                return false;
            }
        }

        // Check the column
        for (int i = 0; i < size; i++) {
            if (currentBoard.getCell(i, col) == value) {
                return false;
            }
        }

        // Check the nxn sub-grid
        int n = (int) Math.sqrt(size);
        int startRow = row - row % n;
        int startCol = col - col % n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (currentBoard.getCell(i + startRow, j + startCol) == value) {
                    return false;
                }
            }
        }

        return true;
    }
    // Finds the most restricted entry and returns an integer array with its line and row
    public int[] findMostRestrictedEntry(IntegerBoard currentBoard) {
        int maxRestrictions = -1; // Tracks the maximum restrictions found
        int[] result = {-1, -1};  // Stores the coordinates of the most restricted cell

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (currentBoard.getCell(row,col) == 0) { // Only consider empty cells
                    int restrictions = countRestrictions(currentBoard, row, col);
                    if (restrictions > maxRestrictions) {
                        maxRestrictions = restrictions;
                        result[0] = row;
                        result[1] = col;
                    }
                }
            }
        }
        return result; // Returns the row and column of the most restricted empty cell
    }
    //Counts how many restrictions a given entry has
    private int countRestrictions(IntegerBoard currentBoard, int row, int col) {
        boolean[] used = new boolean[size+1]; // Tracks numbers already used (1-size)

        // Check the row
        for (int j = 0; j < size; j++) {
            if (currentBoard.getCell(row,j) != 0) {
                used[currentBoard.getCell(row,j)] = true;
            }
        }

        // Check the column
        for (int i = 0; i < size; i++) {
            if (currentBoard.getCell(i,col) != 0) {
                used[currentBoard.getCell(i,col)] = true;
            }
        }

        // Check the nxn sub-grid
        int n = (int) Math.sqrt(size);
        int startRow = row - row % n;
        int startCol = col - col % n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (currentBoard.getCell(i + startRow, j + startCol) != 0) {
                    used[currentBoard.getCell(i + startRow, j + startCol)] = true;
                }
            }
        }

        // Count the number of restrictions (filled numbers in the row, column, and sub-grid)
        int restrictions = 0;
        for (boolean value : used) {
            if (value) restrictions++;
        }
        return restrictions;
    }

}
