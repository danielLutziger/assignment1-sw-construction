package utility;

import grid.Grid;

public class Sunk implements CoordinateState {
    private char state = 's';
    @Override
    public void print(Coordinate coordinate) {
        System.out.println("SUNK");
    }
}
