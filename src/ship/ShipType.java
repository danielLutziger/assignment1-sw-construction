package ship;

public enum ShipType {

    CARRIER(1, 6),
    BATTLESHIP(2, 4),
    SUBMARINE(3, 3),
    PATROL_BOAT(4, 2);

    private final int numberOfShips;
    private final int shipLength;

    ShipType(int numberOfShips, int shipLength) {
        this.numberOfShips = numberOfShips;
        this.shipLength = shipLength;
    }

    public int getNumberOfShips(){
        return numberOfShips;
    }

    public int getShipLength(){
        return shipLength;
    }

}
