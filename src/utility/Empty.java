package utility;


public class Empty implements CoordinateState {
    private static Empty state;

    private Empty(){}

    public static Empty state() {
        if(state == null){
            state = new Empty();
        }
        return state;
    }
    @Override
    public void updateState(Coordinate context)
    {
        System.out.println("Empty");
        context.setState(Empty.state());
    }

    @Override
    public String toString(){
        return " ";
    }
}
