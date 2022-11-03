package utility;

import ship.ShipType;

public class Occupied implements CoordinateState {
    private static Occupied state;
    private char underlyingType;

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
