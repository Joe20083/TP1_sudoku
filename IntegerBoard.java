

public class IntegerBoard<T> implements GameBoard<T>{

    private final int width ;
    private final int height;
    private final T[][] board;

    // Constructor to initialize the board with given width and height
    public IntegerBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = (T[][]) new Object[width][height];  // Create a 2D array for the board
    }


    @Override
    public T getCell(int x, int y) {
        if (isValidCell(x, y)) {
            return board[x][y];
        } else {
            throw new IndexOutOfBoundsException("Invalid cell coordinates");
        }
    }

    @Override
    public void setCell( int x, int y, T value ) {
        if (isValidCell(x, y)) {
            board[x][y] = value;
        } else {
            throw new IndexOutOfBoundsException("Invalid cell coordinates");
        }
    }


    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public int getWidth(){
        returns this.width;
    }


    public int getHeight(){
        returns this.hight;
    }


    public void display(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < hight; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
        }
    }

}
