package grid;

import utility.Coordinate;
import utility.CoordinateState;
import utility.Hit;

public abstract class Grid {
    private final int size = 10;
    private Coordinate[][] grid;


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


    public int getGridSize(){
        return size;
    }

    /**
     * Print the grid
     */
    public void printGrid(){
        System.out.print("======= OCEAN  GRID =======\n");
        System.out.print("    A B C D E F G H I J  \n");
        System.out.print("   +-+-+-+-+-+-+-+-+-+-+ \n");
        for (int row = 0; row < size; row++){
            System.out.print(row+" | ");
            for(int value = 0; value < 10; value++){
                System.out.print(grid[row][value].getState()+"|");
            }
            System.out.print(" " + row+"\n");
        }

        System.out.print("   +-+-+-+-+-+-+-+-+-+-+ \n");
        System.out.print("    A B C D E F G H I J  \n");
        System.out.print("===========================\n");
    }

}
