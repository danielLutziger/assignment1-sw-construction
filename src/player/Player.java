package player;

import game.Coordinates;
import grid.Ocean;
import grid.Target;
import ship.Ship;
import java.util.ArrayList;

public abstract class Player {
    private Ocean ocean;
    private Target target;

    private ArrayList<Ship> ships;

    public Player(){
        this.ocean = new Ocean();
        this.target = new Target();
        this.ships = new ArrayList<>();
    }

    public Ocean getOcean() {
        return ocean;
    }
    public ArrayList<Ship> getShips(){
        return ships;
    }
    protected Target getTarget() {
        return target;
    }
    protected void addShip(Ship ship){
        ships.add(ship);
    }
}
