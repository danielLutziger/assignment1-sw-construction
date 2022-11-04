package utility;

/**
 * Singleton state pattern to represent the hits
 */
public class Hit implements CoordinateState {
    private static Hit state;

    private Hit(){}

    public static Hit state() {
        if(state == null){
            state = new Hit();
        }
        return state;
    }
    @Override
    public void updateState(Coordinate context)
    {
        System.out.println("Hit");
        context.setState(Hit.state());
    }
    @Override
    public String toString(){
        return "X";
    }
}
