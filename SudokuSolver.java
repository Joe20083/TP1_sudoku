public class SudokuSolver implements GameSolver {
    private final IntegerBoard board;        // Original unsolved board
    private IntegerBoard solution;           // Solution board
    private final LinkedGeneralTree<IntegerBoard> solutionTree;



    // Constructor initializes the board and the solution tree
    public SudokuSolver(GameBoard<Integer> board) {
        if (!(board instanceof IntegerBoard)) {
            throw new IllegalArgumentException("Board must be an instance of IntegerBoard.");
        }
        this.board = (IntegerBoard) board;
        this.solution = this.board.clone();  // Initialize solution as a clone of the original board
        this.solutionTree = new LinkedGeneralTree<>(this.solution.clone());  // Tree root as initial board
    }

    // Solves the Sudoku puzzle using backtracking
    @Override
    public boolean solve() {
        // Start solving from the root node of the solution tree
        boolean solved = solveBoard(solutionTree.getRoot());
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

    // Recursive backtracking method to solve the board
    private boolean solveBoard(LinkedGeneralTree.TreeNode<IntegerBoard> currentNode) {
        IntegerBoard currentBoard = currentNode.getElement();

        for (int row = 0; row < currentBoard.getWidth(); row++) {
            for (int col = 0; col < currentBoard.getHeight(); col++) {
                if (currentBoard.getCell(row, col) == 0) {  // Find an empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValidPlacement(currentBoard, row, col, num)) {
                            currentBoard.setCell(row, col, num);  // Place number

                            // Clone the current board and add it as a new node in the tree
                            IntegerBoard nextBoardState = currentBoard.clone();
                            LinkedGeneralTree.TreeNode<IntegerBoard> childNode =
                                    (LinkedGeneralTree.TreeNode<IntegerBoard>) solutionTree.addNode(nextBoardState, currentNode);

                            if (solveBoard(childNode)) {  // Recursively attempt to solve
                                return true;
                            }

                            currentBoard.setCell(row, col, 0);  // Reset cell if no solution found
                        }
                    }
                    return false;  // Return false if no valid number can be placed here
                }
            }
        }
        // Solution found; set this board state as the solution
        solution = currentBoard;
        return true;
    }

    // Checks if placing a number in the cell does not violate Sudoku rules
    public boolean isValidPlacement(IntegerBoard currentBoard, int row, int col, Integer value) {
        // Check the row
        for (int i = 0; i < currentBoard.getWidth(); i++) {
            if (currentBoard.getCell(row, i) == value) {
                return false;
            }
        }

        // Check the column
        for (int i = 0; i < currentBoard.getHeight(); i++) {
            if (currentBoard.getCell(i, col) == value) {
                return false;
            }
        }

        // Check the 3x3 sub-grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentBoard.getCell(i + startRow, j + startCol) == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
