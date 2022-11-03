package player;

import grid.Ocean;
import grid.Target;
import ship.Ship;
import ship.ShipType;
import utility.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
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

    protected Ocean getOcean() {
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
        if (ocean.getGridValue(coordinate).getState() instanceof Occupied) {
            Ship s = getShipFromCoordinate(coordinate);
            s.shipGotHit();
            if (s.getHealth() == 0){
                return new Sunk(s.getShipType());
            } else {
                return Hit.state();
            }

        }
        return Missed.state();
    }

    public boolean didShipSink(Coordinate c){
        if (c.getState() instanceof Sunk) return true;
        return false;
    }

    public ArrayList informAboutSunkenShip(Coordinate c){
        Ship s = getShipFromCoordinate(c);
        return s.getPlacement();
    }

    public void updateTarget(Coordinate coordinate){
        target.updateTarget(coordinate);
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
    public Coordinate attack(){return null;}

    public void drawTarget(){
        this.target.printGrid();
    }
}
