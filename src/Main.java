import player.Computer;
import player.Human;

public class Main {
    public static void main(String[] args) {

        //Human player = new Human();
        Computer ai = new Computer();
        Computer ai2 = new Computer();
        //player.setShips();
        ai.setShips();
        ai2.setShips();

        boolean gameRunning = true;
        do {
            ai2.attack(ai);
            if (ai.isFleetDestroyed()){
                gameRunning = false;
                System.out.println("Winner is AI2");
                break;
            }
            ai.attack(ai2);
            if (ai2.isFleetDestroyed()){
                gameRunning = false;
                System.out.println("Winner is AI");
                break;
            }
        }while(gameRunning);
        //TODO: AI ship placement - here it's mocked

        //TODO: AI shooting behaviour

        //TODO: decide who starts first
        //TODO: start attacks
        //TODO: check whether fleet is sunk
        //TODO: rework the grid layout

        //TODO: refactor code and check whether it is at the right location

        //start round


        /*
         *
         * do turns
         * check if game is over
         *
         */

    }
}