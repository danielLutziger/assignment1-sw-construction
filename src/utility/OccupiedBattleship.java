package utility;

public class OccupiedBattleship implements CoordinateState {
    private static OccupiedBattleship state;

    private OccupiedBattleship(){}

    public static OccupiedBattleship state() {
        if (state == null){
            state = new OccupiedBattleship();
        }
        return state;
    }

    @Override
    public void updateState(Coordinate context)
    {
        System.out.println("OccupiedBattleship");
        context.setState(OccupiedBattleship.state());
    }
    @Override
    public String toString(){
        return "B";
    }
}
