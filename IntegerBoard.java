/**
 * Linked general tree is a class implementing GameBoard<Integer>, it contains methods used for
 * accessing and changing the grid of a given sudoku
 *
 * Based on Goodrich, Tamassia, Goldwasser and the course IFT2015 code
 *
 * @authors Joseph Finan et Lara Simone Suarez Lopez
 * @version     1.0
 * @since       1.0
 */
public class IntegerBoard implements GameBoard<Integer> {
    private final int[][] board;
    private final int size;  // Standard Sudoku grid size

    // Constructor to initialize the board with an existing puzzle
    public IntegerBoard(Integer[][] puzzle) {
        size  = puzzle.length;
        if ( puzzle[0].length != size) {
            throw new IllegalArgumentException(String.format("Puzzle dimensions must be %dx%d.", size, size));
        }
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Check for null and initialize empty cells as 0
                this.board[i][j] = (puzzle[i][j] != null) ? puzzle[i][j] : 0;

            }
        }
    }
    //Returns the value in the (x,y) entry of the board
    @Override
    public Integer getCell(int x, int y) {
        validatePosition(x, y);
        return board[x][y];
    }
    //sets the value of the (x,y)  entry of the board
    @Override
    public void setCell(int x, int y, Integer value) {
        validatePosition(x, y);
        if (value == null || value < 0 || value > size) {
            throw new IllegalArgumentException(String.format("Cell value must be between 0 and %d.", size));
        }
        board[x][y] = value;
    }
    //returns the width of the board
    @Override
    public int getWidth() {
        return size;
    }
    //returns the height of the board
    @Override
    public int getHeight() {
        return size;
    }
    //Prints the board
    @Override
    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Validates if the x and y positions are within the board limits
    private void validatePosition(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new IndexOutOfBoundsException("Position out of board bounds");
        }
    }


}
