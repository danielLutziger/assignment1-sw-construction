import player.Computer;
import player.Player;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //comment for test commit
        /*
         * Create new Grid
         * Init Human / AI
         * Ask for placement
         *
         */
        Player player = new Player();
        Computer ai = new Computer();
        player.setShips();
        ai.setShips();
        for(int x = 0;x<10;x++) {
            player.attack(ai);
            ai.attack(player);
        }
        //TODO: AI ship placement - here it's mocked
        //get input from user for ship placement





        //TODO: AI shooting behaviour
        //


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