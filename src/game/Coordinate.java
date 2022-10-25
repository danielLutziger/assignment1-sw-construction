package game;

public class Coordinate {
    private int x;
    private int y;
    public Coordinate(int x_coordinate, int y_coordinate){
        this.x = x_coordinate;
        this.y = y_coordinate;
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}
}
