package game;

/**
 * Design State Pattern to represent the game state
 */
public enum GameState {
    //possible states
    PLAYER_VICTORY("Player has won the game"),CPU_VICTORY("CPU has won the game"),RUNNING("ongoing");
    private final String message;
    GameState(String message){
        this.message = message;
    }

    public String getMessage(){return message;}
}
