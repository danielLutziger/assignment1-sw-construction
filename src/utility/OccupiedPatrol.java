package utility;

public class OccupiedPatrol implements CoordinateState{
    private static OccupiedPatrol state;

    private OccupiedPatrol(){}

    public static OccupiedPatrol state() {
        if (state == null){
            state = new OccupiedPatrol();
        }
        return state;
    }

    public void updateState(Coordinate context)
    {
        System.out.println("OccupiedPatrol");
        context.setState((CoordinateState) OccupiedPatrol.state());
    }

    @Override
    public String toString(){
        return "P";
    }
}

