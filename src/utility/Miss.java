package utility;

import grid.Grid;

public class Miss implements CoordinateState {
    private char state = 'o';
    @Override
    public void print(Coordinate coordinate) {
        System.out.println("MISS");
    }
}
