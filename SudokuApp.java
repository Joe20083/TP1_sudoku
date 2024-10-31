/**
 * Sudoku application (Main class)
 *
 * @author      Morteza Mahdiani (for TP1-IFT2015-A24)
 * @author      modified by Francois Major, on 24.10.25)
 *              
 * @version     1.1 (25 october 2024)
 * @since       1.0 (22 October 2024)
 */

public class SudokuApp {

    public void testCase1() {
	System.out.println( "*** Test Case 1 ***" );
        Integer[][] puzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        GameBoard<Integer> board = new IntegerBoard( puzzle );
        GameSolver solver = new SudokuSolver( board );
	solver.solve();
	solver.printSolution();
    }

    public void testCase2() {
	System.out.println( "*** Test Case 2 ***" );
        Integer[][] puzzle = {
            {0, 0, 0, 0, 0, 0, 0, 1, 0},
            {4, 0, 3, 0, 0, 5, 0, 0, 0},
            {0, 0, 0, 0, 3, 0, 0, 6, 0},
            {0, 5, 0, 4, 6, 0, 0, 0, 0},
            {0, 0, 0, 3, 0, 8, 0, 0, 0},
            {0, 0, 0, 0, 9, 7, 0, 8, 0},
            {0, 6, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 5, 0, 0, 2, 0, 9},
            {0, 8, 0, 0, 0, 0, 0, 0, 0}
        };
        GameBoard<Integer> board = new IntegerBoard( puzzle );
        GameSolver solver = new SudokuSolver( board );
	solver.solve();
	solver.printSolution();
    }

    public void testCase3() {
	System.out.println( "*** Test Case 3 ***" );
	Integer[][] puzzle = {
	    {5, 1, 6, 8, 4, 9, 7, 3, 2},
	    {3, 0, 7, 6, 0, 5, 0, 0, 0},
	    {8, 0, 9, 7, 0, 0, 0, 6, 5},
	    {1, 3, 5, 0, 6, 0, 9, 0, 7},
	    {4, 7, 2, 5, 9, 1, 0, 0, 6},
	    {9, 6, 8, 3, 7, 0, 0, 5, 0},
	    {2, 5, 3, 1, 8, 6, 0, 7, 4},
	    {6, 8, 4, 2, 0, 7, 5, 0, 0},
	    {7, 9, 1, 0, 5, 0, 6, 0, 0}
	};
        GameBoard<Integer> board = new IntegerBoard( puzzle );
        GameSolver solver = new SudokuSolver( board );
	solver.solve();
	solver.printSolution();
    }

    public void testCase4() {
	System.out.println( "*** Test Case 4 ***" );
        Integer[][] puzzle = {
            {0, 0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 0, 0, 0, 0, 6, 0, 0},
            {0, 0, 0, 5, 0, 9, 0, 0, 0},
            {6, 0, 0, 0, 4, 0, 0, 0, 0},
            {0, 7, 0, 0, 0, 0, 0, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 3, 0},
            {9, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 7, 0, 0, 0, 0},
            {0, 0, 8, 0, 0, 0, 0, 0, 0}
        };
        GameBoard board = new IntegerBoard( puzzle );
        GameSolver solver = new SudokuSolver( board );
	solver.solve();
	solver.printSolution();
    }

    public void testCase5() {
	System.out.println( "*** Test Case 5 ***:" );
        Integer[][] puzzle = {
            {1, 2, 3, 4},
            {3, 4, 0, 0},
            {0, 0, 0, 0},
            {4, 3, 2, 1}
        };
        GameBoard board = new IntegerBoard( puzzle );
        GameSolver solver = new SudokuSolver( board );
	solver.solve();
	solver.printSolution();
    }

    public static void main( String[] args ) {
        SudokuApp test = new SudokuApp();
        test.testCase1();
        test.testCase2();
        test.testCase3();
        test.testCase4();
	test.testCase5();
    }
}
