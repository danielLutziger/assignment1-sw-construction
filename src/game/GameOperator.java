package game;

import player.Computer;
import player.Human;
import player.Player;
import utility.Coordinate;
import utility.CoordinateState;

import java.util.ArrayList;

/**
 * Game Operator Singleton, responsible to start the game and run it
 */
public class GameOperator {
    //Singleton element
    private static GameOperator gameOperator;

    /**
     * instantiate the gameoperator instance
     * @return Singleton instance
     */
    public static GameOperator run(){
        if (gameOperator == null){
            new GameOperator();
        }
        return gameOperator;
    }

    /**
     * Game Operator instructor used to evaluate the game state and run the game
     */
    private GameOperator(){
        Human player = new Human();
        Computer ai = new Computer();
        player.shipPlacement();
        ai.shipPlacement();

        //initialize turns and change the game state
        TurnState turns = TurnState.randomStart();
        GameState gameState = GameState.RUNNING;

        //loop to run turns
        do{
            if(turns.equals(TurnState.PLAYER_TURN)){
                //player turn
                if (attackSequence(player, ai)){
                    gameState = GameState.PLAYER_VICTORY;
                }
            } else {
                //cpu turn
                if (attackSequence(ai, player)){
                    gameState = GameState.CPU_VICTORY;
                }
            }
            turns = TurnState.getOtherState(turns);
        }while(gameState.equals(GameState.RUNNING));
        System.out.println(gameState.getMessage());

        //display winner target and ocean
        if(turns.equals(TurnState.PLAYER_TURN)){
            gameEndSequence(player, ai);
        }else{
            gameEndSequence(ai, player);
        }
    }

    /**
     * the whole attack sequence in a method
     * - the method consists several submethod calls
     * @param attacker: the attacker of the turn
     * @param defender: the defender of the turn
     * @return return if the defender fleet has been destroyed
     */
    private boolean attackSequence(Player attacker, Player defender){
        Coordinate c = attacker.attack();
        CoordinateState cs = defender.underAttack(c);
        c.setState(cs);
        if (defender.didShipSink(c)){
            ArrayList<Coordinate> cords = defender.informAboutSunkenShip(c);
            for (Coordinate cord : cords){
                attacker.updateTarget(cord);
            }
        } else{attacker.updateTarget(c);}
        if(attacker instanceof Human){
            attacker.drawGameFlow();
        }
        return defender.isFleetDestroyed();
    }

    /**
     * the sequence to be executed once the game is over
     * @param attacker: the last attacker
     * @param defender: the last defender
     */
    private void gameEndSequence(Player attacker, Player defender){
        attacker.drawFinal(attacker, defender);
    }
}
