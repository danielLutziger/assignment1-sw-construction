package utility;

import grid.Grid;

public class Hit implements CoordinateState {
    private char state = 'X';
    @Override
    public void print(Coordinate coordinate) {
        System.out.println("HIT");
    }
}
