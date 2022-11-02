package utility;

import java.util.HashMap;

public class CoordinateValue {
    public int x;
    public int y;

    private final int min = 0;
    private final int max = 9;

    CoordinateValue(int x, int y){
        assert x >= min && x <= max;
        assert y >= min && y <= max;
        this.x = x;
        this.y = y;
    }
}
