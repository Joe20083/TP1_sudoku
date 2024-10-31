/**
 * interface for game solvers (player)
 *
 * @author      Francois Major (for TP1-IFT2015-A24)
 *              
 * @version     1.0
 * @since       1.0 (25 October 2024)
 */

public interface GameSolver {

    /**
     * Solves the given game state.
     *
     * @param game the current game state
     * @return the solution, such as the next move or a winning strategy
     */
    boolean solve();

    /**
     * Provides a description of the solution.
     *
     * @param solution the solution obtained from the solve method
     * @return a human-readable explanation of the solution
     */
    void printSolution();
}
