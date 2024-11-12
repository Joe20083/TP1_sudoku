public class IntegerBoard implements GameBoard<Integer> {
    private final int[][] board;
    private final int size;  // Standard Sudoku grid size

    // Constructor to initialize the board with an existing puzzle
    public IntegerBoard(Integer[][] puzzle) {
        size  = puzzle.length;
        if (puzzle.length != size || puzzle[0].length != size) {
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

    @Override
    public Integer getCell(int x, int y) {
        validatePosition(x, y);
        return board[x][y];
    }

    @Override
    public void setCell(int x, int y, Integer value) {
        validatePosition(x, y);
        if (value == null || value < 0 || value > size) {
            throw new IllegalArgumentException("Cell value must be between 0 and 9.");
        }
        board[x][y] = value;
    }

    @Override
    public int getWidth() {
        return size;
    }

    @Override
    public int getHeight() {
        return size;
    }

    @Override
    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Clone method to create a copy of the current board state
    public IntegerBoard clone() {
        IntegerBoard copy = new IntegerBoard(new Integer[size][size]);
        for (int i = 0; i < size; i++) {
            System.arraycopy(this.board[i], 0, copy.board[i], 0, size);
        }
        return copy;
    }



    // Validates if the x and y positions are within the board limits
    private void validatePosition(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new IndexOutOfBoundsException("Position out of board bounds");
        }
    }

}
