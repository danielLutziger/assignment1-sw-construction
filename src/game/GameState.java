package game;

public enum GameState {
    PLAYER_VICTORY("Player has won the game"),CPU_VICTORY("CPU has won the game"),RUNNING("ongoing");
    private final String message;
    GameState(String message){
        this.message = message;
    }
}
