import player.Player;

public class Main {
    public static void main(String[] args) {

        /**
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

        /**
         *
         * do turns
         * check if game is over
         *
         */

    }
}