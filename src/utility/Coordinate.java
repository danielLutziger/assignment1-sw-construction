package utility;

import java.util.HashMap;

public class Coordinate {

    private CoordinateState currentState;
    private final int x;
    private final int y;

    private final char min_x = 'A';
    private final int min_y = 0;
    private final char max_x = 'J';
    private final int max_y = 9;
    private final HashMap<Character, Integer> COORDINATE_MAPPING = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('B', 1);
        put('C', 2);
        put('D', 3);
        put('E', 4);
        put('F', 5);
        put('G', 6);
        put('H', 7);
        put('I', 8);
        put('J', 9);
    }};

    /**
     * Default constructor for first initialization
     * @param y_coordinate
     * @param x_coordinate
     */
    public Coordinate(int y_coordinate, int x_coordinate){
        this.x = x_coordinate;
        this.y = y_coordinate;
        this.currentState = new Empty();
    }

    /**
     * Initialize a coordinate for a human
     * @param coordinate a single coordinate
     * @param state the state for a coordinate : occupy, empty ...
     */
    public Coordinate(String coordinate, CoordinateState state) {
        //validate input
        assert coordinate.length() == 2;
        assert coordinate.charAt(0) >= min_x && coordinate.charAt(0) <= max_x;
        assert Character.getNumericValue(coordinate.charAt(1)) >= min_y && Character.getNumericValue(coordinate.charAt(1)) <= max_y;
        assert state != null;

        this.x = COORDINATE_MAPPING.get(coordinate.charAt(0));
        this.y = Character.getNumericValue(coordinate.charAt(1));
        this.currentState = state;
    }

    /**
     * initialize coordinate for a cpu
     * @param x
     * @param y
     * @param state
     */
    public Coordinate(int x, int y, CoordinateState state) {
        //validate input
        assert x >= 0 && x <= 9;
        assert y >= 0 && y <= 9;
        assert state != null;

        this.x = x;
        this.y = y;
        this.currentState = state;
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public void setState(CoordinateState state)
    {
        currentState = state;
    }

    public CoordinateState getState(){
        return currentState;
    }

    public void printState()
    {
        currentState.print(this);
    }

    public boolean coordinateIsAccessible(Coordinate c){
        if (c.getState() instanceof Empty) return true;
        return false;
    }
}
