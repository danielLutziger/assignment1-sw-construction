package ship;

import utility.Coordinate;
import utility.Occupied;

import java.util.ArrayList;

/**
 * A representation of a ship in the game
 */
public class Ship {
    private final Coordinate start;
    private final Coordinate end;
    private final ArrayList<Coordinate> placement;
    private final ShipType shipType;
    private int health;

    /**
     * Ship constructor for the ship to be created (in the placement)
     * @param start coordinate
     * @param end coordinate
     * @param shipType (carrier, battleship...)
     */
    public Ship(Coordinate start, Coordinate end, ShipType shipType){
        // check if ship is vertical or horizontal (xor gateway)
        assert ((shipType.getShipLength()-1 == (end.getX() - start.getX()) && start.getY() == end.getY())
                ^ (shipType.getShipLength()-1 == (end.getY() - start.getY()) && start.getX() == end.getX()));
        this.shipType = shipType;
        this.start = start;
        this.end = end;
        this.health = shipType.getShipLength();

        placement = new ArrayList<>();
        placement.add(start);
        // fill all the coordinates where the ship is located into placement
        if(end.getX() > start.getX()){
            for (int x = start.getX()+1; x < end.getX(); x++){
                placement.add(new Coordinate(x, end.getY(), new Occupied(shipType)));
            }
        } else {
            for (int y = start.getY()+1; y < end.getY(); y++){
                placement.add(new Coordinate(end.getX(), y, new Occupied(shipType)));
            }
        }
        placement.add(end);
    }

    /**
     * Method to call the health of the ship
     * @return the ship health
     */
    public int getHealth(){
        return health;
    }

    /**
     * Deduct health from a hit ship
     */
    public void shipGotHit(){
        health--;
    }

    /**
     * Get the start coordinate of the ship
     * @return the start coordinate of the ship
     */
    public Coordinate getStart() {
        return start;
    }

    /**
     * Get the end coordinate of the ship
     * @return the end coordinate of the ship
     */
    public Coordinate getEnd() {
        return end;
    }

    /**
     * Get the ship type of the current ship
     * @return the shiptype
     */
    public ShipType getShipType(){return this.shipType;}

    /**
     * Get all the coordinates where the ship is placed on
     * @return all the coordinates of the ships placement
     */
    public ArrayList<Coordinate> getPlacement() {
        return placement;
    }

}
