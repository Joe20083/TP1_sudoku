public class SudokuSolver implements GameSolver {

    // attributes
    IntegerBoard board;
    IntegerBoard solution;
    Tree<IntegerBoard> ...

    // constructor
    public SudokuSolver( GameBoard board )...

    // mandatory GameSolver interface methods
    public boolean solve()...
    public void printSolution()...

    // validate an insertion in the board
    public boolean isValidPlacement( int row, int col, Integer value )...

    // actual solver
    private boolean solveBoard()...
}
