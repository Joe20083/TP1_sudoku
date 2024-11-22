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
        this.solution = this.board;// Initialize solution
        this.solutionTree = new LinkedGeneralTree<>(this.solution);// Tree root as initial board
    }

    // Solves the Sudoku puzzle using backtracking
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

    // Recursive method to solve the board
    private boolean solveBoard(Position<IntegerBoard> currentNode) {

        IntegerBoard currentBoard = currentNode.getElement();
        int size = currentBoard.getWidth();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (currentBoard.getCell(row, col) == 0) {  // Find an empty cell
                    for (int num = 1; num <= size ; num++) {
                        if (isValidPlacement(currentBoard, row, col, num)) {
                            currentBoard.setCell(row, col, num);  // Place number

                            Position<IntegerBoard> childNode = solutionTree.addNode(currentBoard, currentNode);
                            if (solveBoard(childNode)) {  // Recursively attempt to solve
                                return true;
                            }
                            //remove the last child added
                            //System.out.printf("Children %d, %d, %d:%n",row,col,num);
                            //currentBoard.display();

                            //if(solutionTree.numChildren(childNode)==0)solutionTree.remove(childNode);
                            //if(solutionTree.numChildren(solutionTree.parent(childNode))>0)solutionTree.removeBranch(solutionTree.childK(solutionTree.children(solutionTree.parent(childNode)),0));
                            currentBoard.setCell(row, col, 0);  // Reset cell if no solution found
                        }
                        //currentNode.getParent().getChildren().removeFirst();


                    }

                    return false;  // Return false if no valid number can be placed here
                }

               
            }
            System.out.println("Meg used="+(Runtime.getRuntime().totalMemory()-
                    Runtime.getRuntime().freeMemory())/(1000*1000)+"M");
            if(Runtime.getRuntime().freeMemory()>1000*1000 ) currentBoard.display();
            for(Position<IntegerBoard> c :solutionTree.children(solutionTree.parent(currentNode))){
            solutionTree.removeBranch(c);}
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

        // Check the nxn sub-grid
        int n = (int) Math.sqrt(currentBoard.getWidth());
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
}
