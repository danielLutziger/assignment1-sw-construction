package utility;

import grid.Grid;

public class Sunk implements CoordinateState {
    private static Sunk state;

    private Sunk(){}

    public static Sunk state() {
        if(state == null){
            state = new Sunk();
        }
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
        return "S";
    }
}
