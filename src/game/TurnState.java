package game;

import java.util.Random;

public enum TurnState {
    PLAYER_TURN, CPU_TURN;
    private static final Random random = new Random();

    public static TurnState randomStart()  {
        TurnState[] states = values();
        return states[random.nextInt(states.length)];
    }

    public static TurnState getOtherState(TurnState state){
        if (state.equals(PLAYER_TURN))
        {
            return CPU_TURN;
        }
        else
        {
            return PLAYER_TURN;
        }
    }

}
