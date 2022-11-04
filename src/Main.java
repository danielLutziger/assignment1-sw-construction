import game.GameOperator;
import player.Computer;
import player.Human;
import utility.Coordinate;
import utility.CoordinateState;

public class Main {
    public static void main(String[] args) {
        GameOperator.init();
/*        Human player = new Human();
        Human ai = new Human();
        player.shipPlacement();

        Coordinate c = ai.attack();
        CoordinateState cs = player.underAttack(c);
        c.setState(cs);
        ai.updateTarget(c);

        c = ai.attack();
        cs = player.underAttack(c);
        c.setState(cs);
        ai.updateTarget(c);
/*        ai.setShips();
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
*/
    }


    /*
     *
     *
     * TODO: print the game flow in the expected way (outputs from appendix A)
     *
     */

}