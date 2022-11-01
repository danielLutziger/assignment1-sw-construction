package player;

import grid.Ocean;
import grid.Target;
import ship.Ship;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Player {
    private Ocean ocean;
    private Target target; //changed to public to use in inheriting class
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

    public boolean isFleetDestroyed(){
        AtomicInteger health = new AtomicInteger();
        ships.forEach( (ship) -> { health.addAndGet(ship.getHealth()); } );
        if (health.get() > 0){
            return false;
        } else {
            return true;
        }
    }
}
