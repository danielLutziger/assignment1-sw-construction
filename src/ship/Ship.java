package ship;

import game.Coordinate;
import game.Coordinates;

public class Ship {
    private Coordinates coordinates;
    private ShipDirection shipDirection;
    private ShipType shipType;

    public Ship(){
        this.coordinates = null;
        this.shipDirection = null;
        this.shipType = null;
    }

    public Ship(Coordinates coordinates, ShipType shipType) throws ShipDirectionNotValidException{
        this.coordinates = coordinates;
        this.shipType = shipType;
        if (isPlacementValid(coordinates.getStart(), coordinates.getEnd(), shipType.getShipLength())){
            this.shipDirection = getShipDirectionFromPlacement(coordinates.getStart(), coordinates.getEnd());
        }
    }
    private boolean isPlacementValid(Coordinate start, Coordinate end, int shipLength) throws ShipDirectionNotValidException{
        if(start.getX() == end.getX()){
            if(start.getY()+shipLength == end.getY()){
                return true;
            } else {
                throw new ShipDirectionNotValidException("Ship direction is not valid! You can only place it Vertical or Horizontal!");
            }
        } else if (start.getY() == end.getY()){
            if(start.getX()+shipLength == end.getX()){
                return true;
            } else {
                throw new ShipDirectionNotValidException("Ship direction is not valid! You can only place it Vertical or Horizontal!");            }
        } else{
            throw new ShipDirectionNotValidException("Ship direction is not valid! You can only place it Vertical or Horizontal!");        }
    }

    private ShipDirection getShipDirectionFromPlacement(Coordinate start, Coordinate end){
        if (start.getX() > end.getX()){
            return ShipDirection.HORIZONTAL;
        } else {
            return ShipDirection.VERTICAL;
        }
    }
}
