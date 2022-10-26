import player.Player;

public class Main {
    public static void main(String[] args) {

        /*
         * Create new Grid
         * Init Human / AI
         * Ask for placement
         *
         */
        Player player = new Player();
        Player ai = new Player(true);
        player.setShips();
        ai.setShips();
        for(int x = 0;x<10;x++) {
            player.attack(ai);
        }
        //TODO: AI ship placement - here it's mocked


        //TODO: decide who starts first
        //TODO: start attacks
        //

        /*
        Fast placements:

        E9,J9
        G0,J0
        A2,A5
        D0,D2
        F2,H2
        J5,J7
        A0,B0
        A7,B7
        F5,F6
        J2,J3
         */

        //start round


        /*
         *
         * do turns
         * check if game is over
         *
         */

    }
}