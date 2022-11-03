package utility;

import ship.ShipType;

public class Sunk implements CoordinateState {
    private static Sunk state;
    private char underlyingType;
    private Sunk(){}

    public Sunk(ShipType shipType){
        underlyingType = shipType.getAbbreviation();
    }
    public static Sunk state() {
        return state;
    }
    @Override
    public void updateState(Coordinate context)
    {
        System.out.println("Sunk");
        context.setState(Sunk.state());
    }

    @Override
    public String toString(){
        return String.valueOf(underlyingType);
    }
}
