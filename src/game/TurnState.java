package game;

import java.util.Random;

/**
 * Design State Pattern to represent the current turn
 */
public enum TurnState {
    PLAYER_TURN, CPU_TURN;
    private static final Random random = new Random();

    /**
     * Method to define the starting party
     * @return a random start state
     */
    public static TurnState randomStart()  {
        TurnState[] states = values();
        return states[random.nextInt(states.length)];
    }

    /**
     * Change the current state to the opposite (from player to cpu and vice versa)
     * @param state: current state
     * @return the opposite state
     */
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
