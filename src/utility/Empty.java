package utility;

import grid.Grid;

public class Empty implements CoordinateState {
    private char state = ' ';
    @Override
    public void print(Coordinate coordinate) {
        System.out.println("EMPTY");
    }
}
