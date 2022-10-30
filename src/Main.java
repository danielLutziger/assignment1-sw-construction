import player.Computer;
import player.Human;
import player.Player;

public class Main {
    public static void main(String[] args) {

        /*
         * Create new Grid
         * Init Human / AI
         * Ask for placement
         *
         */
        Human player = new Human();
        Computer ai = new Computer();
        //player.setShips();
        ai.setShips();
        /*
        for(int x = 0;x<10;x++) {
          //  player.attack(ai);
        }*/
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