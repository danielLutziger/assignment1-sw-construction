package utility;

/**
 * Singleton state pattern to represent the missed shots
 */
public class Missed implements CoordinateState {
    private static Missed state;

    private Missed(){}

    public static Missed state() {
        if(state == null){
            state = new Missed();
        }
        return state;
    }
    @Override
    public void updateState(Coordinate context)
    {
        System.out.println("Missed");
        context.setState(Missed.state());
    }
    @Override
    public String toString(){
        return "o";
    }
}
