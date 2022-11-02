package utility;

//can be deleted
public class Occupied implements CoordinateState {
    private static Occupied state;

    private Occupied(){}

    public static Occupied state() {
        if (state == null){
            state = new Occupied();
        }
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
        return "O";
    }
}
