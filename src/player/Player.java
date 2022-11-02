package player;

import grid.Ocean;
import grid.Target;
import ship.Ship;
import ship.ShipType;
import utility.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Player {
    private Ocean ocean;
    private Target target;
    private ArrayList<Ship> ships;

    public final HashMap<ShipType, CoordinateState> SHIP_STATE_MAPPING = new HashMap<ShipType, CoordinateState>() {{
        put(ship.ShipType.CARRIER, utility.OccupiedCarrier.state());
        put(ShipType.BATTLESHIP, utility.OccupiedBattleship.state());
        put(ShipType.PATROL_BOAT, utility.OccupiedPatrol.state());
        put(ShipType.SUBMARINE, utility.OccupiedSubmarine.state());
    }};

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

    public CoordinateState underAttack(Coordinate coordinate){
        if (this.ocean.getGridValue(coordinate).getState() instanceof Occupied) {
            Ship s = this.getShipFromCoordinate(coordinate);
            s.shipGotHit();
            return Hit.state();
        }
        return Missed.state();
    }

    public void updateTarget(Coordinate coordinate){
        Coordinate c = target.getGridValue(coordinate);
        c.setState(coordinate.getState());
        target.printGrid();
    }

    public Ship getShipFromCoordinate(Coordinate c){
        for(Ship s : ships){
            for(Coordinate shipCord : s.getPlacement()){
                if(shipCord.getX() == c.getX() && shipCord.getY() == c.getY()){
                    return s;
                }
            }
        }
        return null;
    }
}
