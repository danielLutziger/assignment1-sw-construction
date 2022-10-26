package ship;

import game.Coordinate;
import game.Coordinates;

public class Ship {
    private final Coordinates coordinates;
    private ShipDirection shipDirection;
    private final ShipType shipType;

    private int health;

    public Ship(Coordinates coordinates, ShipType shipType) throws ShipDirectionNotValidException{
        this.coordinates = coordinates;
        this.shipType = shipType;
        if (isPlacementValid(coordinates.getStart(), coordinates.getEnd(), shipType.getShipLength())){
            this.shipDirection = getShipDirectionFromPlacement(coordinates.getStart(), coordinates.getEnd());
        }
        this.health = shipType.getShipLength();
    }
    private boolean isPlacementValid(Coordinate start, Coordinate end, int shipLength) throws ShipDirectionNotValidException{
        if(start.getX() == end.getX()){
            if((start.getY()+shipLength-1) == end.getY()){ //1+6 = 7
                return true;
            } else {
                throw new ShipDirectionNotValidException("Ship direction is not valid or the ship entered does not match the length! You can only place it Vertical or Horizontal!");
            }
        } else if (start.getY() == end.getY()){
            if((start.getX()+shipLength-1) == end.getX()){
                return true;
            } else {
                throw new ShipDirectionNotValidException("Ship direction is not valid or the ship entered does not match the length! You can only place it Vertical or Horizontal!");            }
        } else{
            throw new ShipDirectionNotValidException("Ship direction is not valid or the ship entered does not match the length! You can only place it Vertical or Horizontal!");        }
    }

    private ShipDirection getShipDirectionFromPlacement(Coordinate start, Coordinate end){
        if (start.getX() < end.getX()){
            return ShipDirection.VERTICAL;
        } else {
            return ShipDirection.HORIZONTAL;
        }
    }

    public int getHealth(){
        return health;
    }
    public void shipGotHit(){
        health--;
    }
    public Coordinates getCoordinates(){return this.coordinates;}
    public ShipDirection getShipDirection(){return this.shipDirection;}
    public ShipType getShipType(){return this.shipType;}
}
