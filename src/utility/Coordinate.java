package utility;

import java.util.HashMap;

/**
 * Representation of a coordinate
 */
public class Coordinate {

    // current state of the coordinate
    private CoordinateState currentState;
    //x in a computer matrix is vertical
    private final int x;
    //y in a computer matrix horizontal
    private final int y;

    //values for the input validation
    private final char min_x = 'A';
    private final int min_y = 0;
    private final char max_x = 'J';
    private final int max_y = 9;

    //all the possible values on the grid
    private final HashMap<Character, Integer> COORDINATE_MAPPING = new HashMap<>() {{
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
     * @param y_coordinate number generated from cpu
     * @param x_coordinate number generated from cpu
     */
    public Coordinate(int y_coordinate, int x_coordinate){
        //validate random generated coordinate input
        assert y_coordinate >= min_y && y_coordinate <= max_y;
        assert x_coordinate >= min_y && x_coordinate <= max_y;

        this.x = x_coordinate;
        this.y = y_coordinate;
        this.currentState = Empty.state();
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

        this.y = COORDINATE_MAPPING.get(coordinate.charAt(0));
        this.x = Character.getNumericValue(coordinate.charAt(1));
        this.currentState = state;
    }

    /**
     * initialize coordinate for a cpu
     * @param x number generated from cpu
     * @param y number generated from cpu
     * @param state new state for the coord
     */
    public Coordinate(int x, int y, CoordinateState state) {
        //validate input
        assert x >= 0 && x <= 9;
        assert y >= 0 && y <= 9;
        assert state != null;

        this.y = y;
        this.x = x;
        this.currentState = state;
    }

    /**
     * Initialize a coordinate for a human
     * @param coordinate a single coordinate
     */
    public Coordinate(String coordinate) {
        //validate input
        assert coordinate.length() == 2;
        assert coordinate.charAt(0) >= min_x && coordinate.charAt(0) <= max_x;
        assert Character.getNumericValue(coordinate.charAt(1)) >= min_y && Character.getNumericValue(coordinate.charAt(1)) <= max_y;

        this.y = COORDINATE_MAPPING.get(coordinate.charAt(0));
        this.x = Character.getNumericValue(coordinate.charAt(1));
        this.currentState = null;
    }

    /**
     * Get the X value of the coordinate
     * @return x value of the coordinate
     */
    public int getX(){return this.x;}
    /**
     * Get the Y value of the coordinate
     * @return y value of the coordinate
     */
    public int getY(){return this.y;}

    /**
     * Set the state of the coordinate
     * @param state the new state
     */
    public void setState(CoordinateState state)
    {
        currentState = state;
    }

    /**
     * Get the state of the coordinate
     * @return currentState of the coordinate
     */
    public CoordinateState getState(){
        return currentState;
    }

}
