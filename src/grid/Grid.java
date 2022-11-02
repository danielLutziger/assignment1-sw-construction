package grid;

import utility.Coordinate;
import utility.CoordinateState;
import utility.Empty;
import utility.Hit;

public abstract class Grid {
    public final int size = 10;
    private Coordinate[][] grid;

    //grid should only contain helper functions
    public Grid(){
        grid = new Coordinate[size][size];
        for (int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                grid[row][column] = new Coordinate(row, column);
            }
        }
    }

    protected void setGrid(Coordinate[][] grid){
        this.grid = grid;
    }
    protected Coordinate[][] getGrid(){
        return grid;
    }

    public Coordinate getGridValue(Coordinate coordinate){
        return grid[coordinate.getX()][coordinate.getY()];
    }

    public Coordinate getGridValue(int x, int y){
        return grid[x][y];
    }

    //bad practice to give access to grid but???
    public void setFieldState(int x, int y, CoordinateState state){
        this.grid[x][y].setState(state);
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
                System.out.print(" " + grid[row][value].getState().toString());
            }
            System.out.print(" | " + row);
        }
        System.out.print("\n=============================\n");
        System.out.print("     A B C D E F G H I J\n\n");
    }
}
