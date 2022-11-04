package utility;

import ship.ShipType;

/**
 * State pattern to represent the occupied coordinates
 * Singleton here is not possible as the coordinate can be occupied but the underlying ship is a different one
 */
public class Occupied implements CoordinateState {
    private static Occupied state;
    private char underlyingType;

    /**
     * Create a new occupied state with the abbreviation
     * @param shipType to get the abbreviation
     */
    public Occupied(ShipType shipType){
        underlyingType = shipType.getAbbreviation();
    }

    public static Occupied state() {
        return state;
    }
    @Override
    public void updateState(Coordinate context)
    {
        System.out.println("Occupied");
        context.setState(Occupied.state());
    }

    @Override
    public String toString(){
        return String.valueOf(underlyingType);
    }
}
