package grid;

import utility.Coordinate;
import utility.Empty;

/**
 * Abstract class Grid
 * - Subclasses are Ocean and Target, which will be used to log the shots and boats
 */
public abstract class Grid {
    //define the grid size
    private final int SIZE = 10;
    //grid consists of a m x n coordinate array
    private Coordinate[][] grid;
    //the name for the grid (ocean or target) - will be used in the print method
    private String name;


    public Grid(){
        grid = new Coordinate[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++){
            for(int column = 0; column < SIZE; column++){
                grid[row][column] = new Coordinate(row, column);
            }
        }
    }

    /**
     * Method to set the grid and overwrite the current (for changes)
     * @param grid how to overwrite the grid
     */
    protected void setGrid(Coordinate[][] grid){
        this.grid = grid;
    }

    /**
     * Return the current grid
     * @return the current grid
     */
    protected Coordinate[][] getGrid(){
        return grid;
    }

    /**
     * Method to get the reference of teh coordinate on the grid
     * @param coordinate coordinate which will be looked up on the grid
     * @return coordinate from the grid
     */
    public Coordinate getGridValue(Coordinate coordinate){
        return grid[coordinate.getX()][coordinate.getY()];
    }

    /**
     * method to access the gridsize
     * @return gridsize
     */
    public int getGridSize(){
        return SIZE;
    }

    /**
     *
     * @param name the gird name (OCEAN | TARGET)
     */
    protected void setName(String name){this.name=name;}
    /**
     * Print the grid
     */
    public void printGrid(){
        System.out.print("=======  "+name+" GRID =======\n");
        System.out.print("   A B C D E F G H I J  \n");
        System.out.print("  +-+-+-+-+-+-+-+-+-+-+ \n");
        for (int row = 0; row < SIZE; row++){
            System.out.print(row+" |");
            for(int value = 0; value < 10; value++){
                System.out.print(grid[row][value].getState()+"|");
            }
            System.out.print(" " + row+"\n");
        }

        System.out.print("   +-+-+-+-+-+-+-+-+-+-+ \n");
        System.out.print("    A B C D E F G H I J  \n");
        System.out.print("===========================\n");
    }

    /**
     * Draw the final grid once the game is finished -> map the loser target onto the winner ocean to show the hits, misses and sunken ships
     * @param o: ocean of the winner
     * @param t: target of the loser
     */
    public void drawFinal(Ocean o, Target t){

        System.out.print("=======  FINAL GRID =======\n");
        System.out.print("   A B C D E F G H I J  \n");
        System.out.print("  +-+-+-+-+-+-+-+-+-+-+ \n");
        for (int row = 0; row < SIZE; row++){
            System.out.print(row+" |");
            for(int value = 0; value < 10; value++){
                if (t.getGrid()[row][value].getState() instanceof Empty){
                    System.out.print(o.getGrid()[row][value].getState()+"|");
                } else{
                    System.out.print(t.getGrid()[row][value].getState()+"|");
                }
            }
            System.out.print(" " + row+"\n");
        }
        System.out.print("   +-+-+-+-+-+-+-+-+-+-+ \n");
        System.out.print("    A B C D E F G H I J  \n");
        System.out.print("===========================\n");
    }

}
