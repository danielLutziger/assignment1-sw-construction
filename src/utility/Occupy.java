package utility;

import grid.Grid;

public class Occupy implements CoordinateState {
    private char state = 'f';
    @Override
    public void print(Coordinate coordinate) {
        System.out.println("OCCUPIED");
    }
}
