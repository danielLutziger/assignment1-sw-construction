package utility;

import java.util.HashMap;

public class IO {

    static private final char min_x = 'A';
    static private final int min_y = 0;
    static private final char max_x = 'J';
    static private final int max_y = 9;
    static private final HashMap<Character, Integer> COORDINATE_MAPPING = new HashMap<Character, Integer>() {{
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

    static public CoordinateValue getCoordinates(String coordinateInput) {
        assert coordinateInput.length() == 2;
        assert coordinateInput.charAt(0) >= min_x && coordinateInput.charAt(0) <= max_x;
        assert Character.getNumericValue(coordinateInput.charAt(1)) >= min_y && Character.getNumericValue(coordinateInput.charAt(1)) <= max_y;

        int x = COORDINATE_MAPPING.get(coordinateInput.charAt(0));
        int y = Character.getNumericValue(coordinateInput.charAt(1));
        return new CoordinateValue(x,y);
    }
}
