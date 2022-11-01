package utility;

public class Coordinate {
    private final int x;
    private final int y;
    public Coordinate(int y_coordinate, int x_coordinate){
        this.x = x_coordinate;
        this.y = y_coordinate;
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}
}
