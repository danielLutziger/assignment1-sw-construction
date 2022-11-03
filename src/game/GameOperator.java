package game;

import player.Computer;
import player.Human;
import player.Player;
import utility.Coordinate;
import utility.CoordinateState;

public class GameOperator {
    private static GameOperator gameOperator;
    private GameOperator(){
        Human player = new Human();
        Computer ai = new Computer();
        player.shipPlacement();
        ai.shipPlacement();


        TurnState turns = TurnState.randomStart();

        do{
            if(turns.equals(TurnState.PLAYER_TURN)){
                attackSequence(player, ai);
            } else {
                attackSequence(ai, player);
            }
            turns = TurnState.getOtherState(turns);
        }while(true);

    }
    public void attackSequence(Player attacker, Player defender){
        Coordinate c = attacker.attack();
        CoordinateState cs = defender.underAttack(c);
        c.setState(cs);
        attacker.updateTarget(c);
    }
    public static void init(){
        if (gameOperator == null){
            new GameOperator();
        }
    }
}
