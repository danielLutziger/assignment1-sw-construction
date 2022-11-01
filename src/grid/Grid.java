package grid;

public abstract class Grid {
    protected final int size = 10;
    private char[][] grid;

    protected final char INIT_FILLER_VALUE = 'X';

    /**
     * Constructor of the grid
     * Fill the grid with 'X' values
     */
    public Grid(){
        grid = new char[this.size][this.size];
        for (int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                grid[row][column] = INIT_FILLER_VALUE;
            }
        }
    }

    protected void setGrid(char[][] grid){
        this.grid = grid;
    }
    protected char[][] getGrid(){
        return grid;
    }

    public int getGridSize(){
        return size;
    }

    /**
     * Print the grid
     */
    public void printGrid(){
        for (int row = 0; row < size; row++){
            System.out.println(grid[row]);
        }
    }
}
