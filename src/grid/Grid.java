package grid;

public abstract class Grid {
    private final int size = 10;
    private char[][] grid;

    public Grid(){
        grid = new char[this.size][this.size];
        //fill Grid with 'x'
        for (int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                grid[row][column] = 'X';
            }
        }
    }

    public void printGrid(){
        for (int row = 0; row < size; row++){
            System.out.println(grid[row]);
        }
    }
}
