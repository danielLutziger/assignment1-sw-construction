package utility;

public class OccupiedSubmarine implements CoordinateState {
    private static OccupiedSubmarine state;

    private OccupiedSubmarine(){}

    public static OccupiedSubmarine state() {
        if (state == null){
            state = new OccupiedSubmarine();
        }
        return state;
    }

    public void updateState(Coordinate context)
    {
        System.out.println("OccupiedSubmarine");
        context.setState((CoordinateState) OccupiedSubmarine.state());
    }

    @Override
    public String toString(){
        return "S";
    }
}
