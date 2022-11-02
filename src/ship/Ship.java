package ship;

import utility.Coordinate;
import utility.Occupied;

import java.util.ArrayList;

public class Ship {
    private final Coordinate start;
    private final Coordinate end;

    private final ShipType shipType;
    private int health;

    public Ship(Coordinate start, Coordinate end, ShipType shipType){
        // check if ship is vertical or horizontal (xor gateway)
        assert ((shipType.getShipLength()-1 == (end.getX() - start.getX()) && start.getY() == end.getY())
                ^ (shipType.getShipLength()-1 == (end.getY() - start.getY()) && start.getX() == end.getX()));
        this.shipType = shipType;
        this.start = start;
        this.end = end;
        this.health = shipType.getShipLength();
    }

    public int getHealth(){
        return health;
    }
    public void shipGotHit(){
        health--;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public ShipType getShipType(){return this.shipType;}

    //public ArrayList<Coordinate> getPlacement() {
    //    return placement;
    //}

}
