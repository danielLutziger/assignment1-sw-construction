package player;

import utility.Coordinate;
import ship.Ship;
import ship.ShipType;
import utility.Empty;
import utility.Occupied;
import java.util.Scanner;

//imports for mock
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;

/**
 * Human class to represent a physical player
 */
public class Human extends Player {

    public Human(){}

    /**
     * Method to place the ships for the human
     * The ships will be placed based on the input
     */
    @Override
    public void shipPlacement() {
        for (ShipType shipType : ShipType.values()){
            for(int shipFromType = 0; shipFromType < shipType.getNumberOfShips(); shipFromType++){
                boolean entered_unsuccessfully = true;
                do {
                    System.out.println("Where do you want to place your " + shipType + " (size: " + shipType.getShipLength() + ") (Ship no. " + (shipFromType + 1) + "/ " + shipType.getNumberOfShips() + ")\n" +
                            "Enter Start end End Coordinates (e.g. A1,A" + shipType.getShipLength() + ")");
                    try {
                        /*
                        //MOCK by deleting the comment from the line below and the associated method, userinput can be mocked
                        String[] coords = new Scanner(mockShipPlacement()).next().split(",");
                         */
                        String[] coords = new Scanner(System.in).next().split(",");
                        Coordinate start = new Coordinate(coords[0], new Occupied(shipType));
                        Coordinate end = new Coordinate(coords[1], new Occupied(shipType));
                        // check ship placement
                        if (this.getOcean().placeShip(start, end)){
                            Ship ship = new Ship(start, end, shipType);
                            addShip(ship);
                            entered_unsuccessfully = false;
                            getOcean().updateOcean(ship);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("\n\nEnter them again...\n");
                    }

                }while(entered_unsuccessfully);
                this.getOcean().printGrid();
            }
        }
    }

    /**
     * Human method to attack the opponent
     * @return the coordinate to be attacked
     */
    @Override
    public Coordinate attack() {
        boolean unsuccessfulAttack = true;
        Coordinate coordinate = null;
        do {
            System.out.println("Attack attack attack, Captain enter the coordinates");
            try {
                coordinate = new Coordinate(new Scanner(System.in).next(), Empty.state());
                if (getTarget().isTargetAttackable(coordinate)){
                    unsuccessfulAttack = false;
                } else {
                    System.out.println("\nTarget already attacked\n\n");
                }
            } catch (Exception e) {
                System.out.println("\nPlease enter valid coordinates...\n\n");
                System.out.println("Specific error message");
                System.out.println(e.getMessage());
                System.out.println("\n\nEnter them again...\n");
            }
        } while(unsuccessfulAttack);

        return coordinate;
    }
/*
    private int counter = -1;

    //method for mock placement of the input, returns one value pair at the time
    private InputStream mockShipPlacement(){
        counter++;
        String[] pm = {"E9,J9",
                "G0,J0",
                "A2,A5",
                "D2,F2",
                "G3,I3",
                "J5,J7",
                "A0,B0",
                "A7,B7",
                "F5,F6",
                "J2,J3"};
        InputStream in = new ByteArrayInputStream(pm[counter].getBytes());
        return in;
    }

 */
}
