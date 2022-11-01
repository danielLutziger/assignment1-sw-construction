import player.Computer;
import player.Human;

public class Main {
    public static void main(String[] args) {

        Human player = new Human();
        Computer ai = new Computer();
        player.setShips();
        ai.setShips();
        do {
            player.attack(ai);
            if (ai.isFleetDestroyed()){
                System.out.println("Winner is player");
                break;
            }
            ai.attack(player);
            if (player.isFleetDestroyed()){
                System.out.println("Winner is AI");
                break;
            }
        }while(true);

    }
}