/**
 * interface for grid-oriented game boards storing generic type values
 *
 * @author      Francois Major (for TP1-IFT2015-A24)
 *              
 * @version     1.0
 * @since       1.0 (25 October 2024)
 */

public interface GameBoard<T> {
    
    /**
     * Retrieves the value at the specified position.
     *
     * @param x the column index (0-based)
     * @param y the row index (0-based)
     * @return the value at the specified position
     * @throws IndexOutOfBoundsException if the position is out of bounds
     */
    T getCell( int x, int y ) throws IndexOutOfBoundsException;
    
    /**
     * Sets the value at the specified position.
     *
     * @param x the column index (0-based)
     * @param y the row index (0-based)
     * @param value the value to set
     * @throws IndexOutOfBoundsException if the position is out of bounds
     */
    void setCell( int x, int y, T value ) throws IndexOutOfBoundsException;
    
    /**
     * Returns the width of the game board.
     *
     * @return the number of columns
     */
    int getWidth();
    
    /**
     * Returns the height of the game board.
     *
     * @return the number of rows
     */
    int getHeight();
    
    /**
     * Displays the game board.
     */
    void display();
}
