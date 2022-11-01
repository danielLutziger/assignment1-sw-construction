package grid;

public abstract class Grid {
    private final int size = 10;
    private char[][] grid;

    protected final char INIT_FILLER_VALUE = ' ';

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
        System.out.print("\n     A B C D E F G H I J");
        System.out.print("\n=============================");
        for (int row = 0; row < size; row++){
            System.out.print("\n"+row+" | ");
            for(int value = 0; value < 10; value++){
                System.out.print(" " + grid[row][value]);
            }
            System.out.print(" | " + row);
        }
        System.out.print("\n=============================\n");
        System.out.print("     A B C D E F G H I J\n\n");
    }
}
