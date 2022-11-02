package utility;

import java.util.HashMap;
import java.util.Map;

public class Coordinates {
    private Coordinate start;
    private Coordinate end;

    private Coordinate attack;

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
     *
     * @param coordinates the full coordinates are expected. Faulty inputs cannot be handled.
     *                   An example of the full coordinates look like this
     *                   A1,A5 || A2, A7
     */
    public Coordinates(String[] coordinates) throws CoordinatesOutOfBoundsException {

        //TODO: assert coordiantePairs.length > 1 && coordiantePairs[0].length() == 2 && coordiantePairs[1].length() == 2 ;

        this.start = placement(coordinates[0]);
        this.end = placement(coordinates[1]);
    }

    public Coordinates(String coordinate) throws CoordinatesOutOfBoundsException {
        // new single coordinate
        this.attack = placement(coordinate);
    }

    public Coordinates(int direction, int shipLength, int randomX, int randomY) throws CoordinatesOutOfBoundsException {
        //get key from value
        String coordvalue = getKeyFromValue(randomY) + Integer.toString(randomX);
        this.start = placement(coordvalue);
        //generate random coordinate
        if (direction == 1){
            //horizontal
            coordvalue = getKeyFromValue(randomY+shipLength-1) + Integer.toString(randomX);
        }else {
            //vertical
            coordvalue = getKeyFromValue(randomY) + Integer.toString(randomX+shipLength-1);
        }
        this.end = placement(coordvalue);
    }

    public Coordinates(int x, int y) throws CoordinatesOutOfBoundsException {
        //get key from value
        String coordvalue = getKeyFromValue(x) + Integer.toString(y);
        this.attack = placement(coordvalue);
    }

    private char getKeyFromValue(int value){
        for(Map.Entry<Character, Integer> entry: COORDINATE_MAPPING.entrySet()){
            if (entry.getValue() == value){
                return entry.getKey();
            }
        }
        return ' ';
    }

    /**
     * TODO: METHOD currenly only takes char at 1 for the int values A12 => A1 => which is valid!!
     * Method for the coordinate creation
     * @param stringCoordinateValue: the coordinate value which the user has entered
     * @return return the Coordinate object
     * @throws CoordinatesOutOfBoundsException: in case of invalid Coordinates a new OutOfBounds exception will be thorwn
     */
    private Coordinate placement(String stringCoordinateValue) throws CoordinatesOutOfBoundsException {
        try {
            if (COORDINATE_MAPPING.containsValue(Character.getNumericValue(stringCoordinateValue.strip().charAt(1)))){
                return new Coordinate(COORDINATE_MAPPING.get(stringCoordinateValue.strip().charAt(0)), Character.getNumericValue(stringCoordinateValue.strip().charAt(1)));
            } else {
                throw new CoordinatesOutOfBoundsException("The Coordinates " + stringCoordinateValue+" you have entered are invalid");
            }
        }catch (NullPointerException e){
            // Throw new Exception that
            throw new CoordinatesOutOfBoundsException("The Coordinates " + stringCoordinateValue+" you have entered are invalid");
        }
    }

    /**
     * TODO: Method is not used!
     * Method to check if a ship is out of bounds
     * @return isOutOfBounds
     */
    private boolean isOutOfBounds(){
        return false;
    }
    public Coordinate getStart(){
        return start;
    }
    public Coordinate getEnd(){
        return end;
    }
    public Coordinate getAttack(){return attack;}


}
