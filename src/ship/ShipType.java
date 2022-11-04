package ship;

/**
 * Enum with all the different ship types
 */
public enum ShipType {

    //ships in the game
    CARRIER(1, 6, 'C'),
    BATTLESHIP(2, 4, 'B'),
    SUBMARINE(3, 3, 'S'),
    PATROL_BOAT(4, 2, 'P');

    private final int numberOfShips;
    private final int shipLength;

    private final char abbreviation;

    /**
     * Constructor to create a new ship type
     * @param numberOfShips the amount of ships of this type existing in the game
     * @param shipLength the length of the ship
     * @param abbreviation the abbreviation for the ship in the game
     */
    ShipType(int numberOfShips, int shipLength, char abbreviation) {
        this.numberOfShips = numberOfShips;
        this.shipLength = shipLength;
        this.abbreviation = abbreviation;
    }

    /**
     * Get the number of the ship types
     * @return number of ships from a certain type
     */
    public int getNumberOfShips(){
        return numberOfShips;
    }

    /**
     * Get the ship length
     * @return the length of the ship in question
     */
    public int getShipLength(){
        return shipLength;
    }

    /**
     * Get the ship abbreviation
     * @return the ship abbreviation
     */
    public char getAbbreviation(){return abbreviation;}

}
