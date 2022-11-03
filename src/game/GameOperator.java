package game;

import player.Computer;
import player.Human;
import player.Player;
import utility.Coordinate;
import utility.CoordinateState;

import java.util.ArrayList;

public class GameOperator {
    private static GameOperator gameOperator;
    private GameOperator(){
        Human player = new Human();
        Computer ai = new Computer();
        player.shipPlacement();
        ai.shipPlacement();


        TurnState turns = TurnState.randomStart();
        GameState gameState = GameState.RUNNING;

        do{
            if(turns.equals(TurnState.PLAYER_TURN)){
                if (attackSequence(player, ai)){
                    gameState = GameState.PLAYER_VICTORY;
                }
            } else {
                if (attackSequence(ai, player)){
                    gameState = GameState.CPU_VICTORY;
                }
            }
            turns = TurnState.getOtherState(turns);
        }while(gameState.equals(GameState.RUNNING));
        System.out.println(gameState);

        //winner ocean anzeigen
        if(turns.equals(TurnState.PLAYER_TURN)){
            gameEndSequence(player);
        }else{
            gameEndSequence(ai);
        }
    }
    public boolean attackSequence(Player attacker, Player defender){
        Coordinate c = attacker.attack();
        CoordinateState cs = defender.underAttack(c);
        c.setState(cs);
        if (defender.didShipSink(c)){
            ArrayList<Coordinate> cords = defender.informAboutSunkenShip(c);
            for (Coordinate cord : cords){
                attacker.updateTarget(cord);
            }
        } else{attacker.updateTarget(c);}
        attacker.drawTarget();
        return defender.isFleetDestroyed();
    }
    public static void init(){
        if (gameOperator == null){
            new GameOperator();
        }
    }

    public void gameEndSequence(Player attacker){
        attacker.drawOcean();
    }
}
