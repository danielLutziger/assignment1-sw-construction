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
        ai.getOcean().printGrid();
        player.setShips();
        //ai.getGrid().printGrid();

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

        /*
         *
         * do turns
         * check if game is over
         *
         */
        /*
         * TODO: Coordinates in ENTRY somehow do not add up with ship length => A0,A6 => length 7 instead of 6
         * TODO: last value on grid cannot be accessed => value number 9 (same for rows)
         */

    }
}