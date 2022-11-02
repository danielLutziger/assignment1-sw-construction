package utility;

public class Coordinate {

    private CoordinateState currentState;
    private final int x;
    private final int y;

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


    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public void setState(CoordinateState state)
    {
        currentState = state;
    }

    public void printState()
    {
        currentState.print(this);
    }
}
