package ship;

public enum ShipType {

    CARRIER(1, 6, 'C'),
    BATTLESHIP(2, 4, 'B'),
    SUBMARINE(3, 3, 'S'),
    PATROL_BOAT(4, 2, 'P');

    private final int numberOfShips;
    private final int shipLength;

    private final char abbreviation;

    ShipType(int numberOfShips, int shipLength, char abbreviation) {
        this.numberOfShips = numberOfShips;
        this.shipLength = shipLength;
        this.abbreviation = abbreviation;
    }

    public int getNumberOfShips(){
        return numberOfShips;
    }

    public int getShipLength(){
        return shipLength;
    }

    public char getAbbreviation(){return abbreviation;}

}
