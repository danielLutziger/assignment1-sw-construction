package ship;

import game.Coordinates;

public class Ship {

    private final int length;
    private Coordinates coordinates;
    private ShipDirection shipDirection;
    private ShipType shipType;

    public Ship(String name, int length){
        this.length = length;
        this.coordinates = null;
        this.shipDirection = null;
        this.shipType = null;
    }

    public Ship(String name, int length, Coordinates coordinates, ShipDirection shipDirection, ShipType shipType){
        this.length = length;
        this.coordinates = coordinates;
        this.shipDirection = shipDirection;
        this.shipType = shipType;
    }

}
