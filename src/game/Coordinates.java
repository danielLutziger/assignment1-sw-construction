package game;

import java.util.HashMap;

public class Coordinates {
    private int x;
    private int y;

    private final HashMap<Character, Integer> X_COORDINATE_MAPPING = new HashMap<Character, Integer>() {{
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

    public Coordinates(){
        this.x = 0;
        this.y = 0;
    }

    /**
     *
     * @param coordinates the full coordinates are expected. Faulty inputs cannot be handled.
     *                   An example of the full coordinates look like this
     *                   A1,A5 || A2, A7
     */
    public Coordinates(String coordinates) throws CoordinatesOutOfBoundsException {
        // Entry looks like this:  A2,A5
        String[] coordiantePairs = coordinates.split(",");
        if (coordiantePairs.length > 1){
            for (String coords : coordiantePairs){
                new Coordinates(coords);
            }
        } else {
            try {
                this.x = X_COORDINATE_MAPPING.get(coordinates.strip().charAt(0));
                this.y = Character.getNumericValue(coordinates.strip().charAt(1));
            } catch (NullPointerException e){
                // Throw new Exception that
                throw new CoordinatesOutOfBoundsException("The Coordinates " + coordinates+" you have entered are invalid");
            }
        }
    }

    /**
     * Method to check if a ship is out of bounds
     * @return isOutOfBounds
     */
    private boolean isOutOfBounds(){
        return false;
    }



}
