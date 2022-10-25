package game;

import java.util.HashMap;

public class Coordinates {
    private Coordinate start;
    private Coordinate end;

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
    public Coordinates(String coordinates) throws CoordinatesOutOfBoundsException {
        // Entry looks like this:  A2,A5
        String[] coordiantePairs = coordinates.split(",");
        //assert coordiantePairs.length > 1 && coordiantePairs[0].length() == 2 && coordiantePairs[1].length() == 2 ;

        this.start = placement(coordiantePairs[0]);
        this.end = placement(coordiantePairs[1]);

    }

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


}