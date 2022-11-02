import player.Computer;
import player.Human;

public class Main {
    public static void main(String[] args) {

        Human player = new Human();
        Computer ai = new Computer();
        player.shipPlacement();
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
     * TODO: check input if only length 2 (for attacks and coordinates)
     * TODO: print the grid in the expected way
     * TODO: print the game flow in the expected way (outputs from appendix A)
     * TODO: replace the H from Hit with an X
     * TODO: recreate the attack method in a way that the elements are not changeable
     * TODO: show a final grid (ocean grid of the winner)
     * TODO: player start random
     * TODO: shots are recorded on ocean and target grid
     *
     * Design state pattern
     * User -> has fleet -> fleet consists of ships -> ships have state (occupied / empty)
     * User -> has target -> target consists of coordinates -> coordinates have state (hit, miss, sunk, occupied, empty)
     *
     * attack:
     * Player1 -> enter coordinates -> pass them to other player2 -> other player checks in his fleet -> returns state (hit, miss, sunk) -> player2 changes state in ocean -> returned state used for player1 to change target
     *
     * overwriteFunction => to update target grid
     *
     * interface CoordinateState
     * > printMethod
     *
     * class Grid
     * > creates a coordinate and sets state to empty
     * > method setState (CoordinateState state) -> sets state of coordinate
     * > printMethod () prints current state
     *
     * class Empty | Hit | Miss | Sunk | Occupied implements CoordinateState
     * @Override printMethod -> print state if required
     *
     *
     * use asserts instead of exceptions
     *
     *
//     * Iterator Pattern
//     * -> iterate through the ship when sunk
//     * -> change the char of the ship
     *
     *
     * Input validation => coordinates
     * assert len == 2
     * assert input >= A && input <= J
     * assert input >= 0 && input <= 9
     *    ok.
     *
     * Coordinates => select random | select manual
     *
     *
     * new human / pc
     * put into player arraylist of player and shuffle
     * do while fleetIsAlive() (method coming from enemy player){
     * for element in arraylist
     *
     * }
     *
     *
     * coordinates => aufteilen und im schiff zuweisen
     * - coordinates => X / Y
     * - schiff => start und end
     */

}