package utility;

public class OccupiedCarrier implements CoordinateState {
    private static OccupiedCarrier state;

    private OccupiedCarrier(){}

    public static OccupiedCarrier state() {
        if (state == null){
            state = new OccupiedCarrier();
        }
        return state;
    }

    public void updateState(Coordinate context)
    {
        System.out.println("OccupiedCarrier");
        context.setState((CoordinateState) OccupiedCarrier.state());
    }

    @Override
    public String toString(){
        return "C";
    }
}
