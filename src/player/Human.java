package player;

import utility.Coordinate;
import ship.Ship;
import ship.ShipType;
import utility.Empty;
import utility.Occupied;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Human extends Player {

    public Human(){}

    public void shipPlacement() {
        for (ShipType shipType : ShipType.values()){
            for(int shipFromType = 0; shipFromType < shipType.getNumberOfShips(); shipFromType++){
                boolean entered_unsuccessfully = true;
                do {
                    System.out.println("Where do you want to place your " + shipType + " (size: " + shipType.getShipLength() + ") (Ship no. " + (shipFromType + 1) + "/ " + shipType.getNumberOfShips() + ")\n" +
                            "Enter Start end End Coordinates (e.g. A1,A" + shipType.getShipLength() + ")");
                    try {
                        //String[] coords = new Scanner(System.in).next().split(",");
                        //MOCK
                        String[] coords = new Scanner(mockShipPlacement()).next().split(",");
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

    public Coordinate attack() {
        boolean unsuccessfulAttack = true;
        Coordinate coordinate = null;
        do {
            System.out.println("Attack attack attack, Captain enter the coordinates");
            try {
                coordinate = new Coordinate(new Scanner(System.in).next(), Empty.state());
                //getTarget().shipAttack(coordinate, enemy);
                unsuccessfulAttack = false;
            } catch (Exception e) {
                System.out.println("\nPlease enter valid coordinates...\n\n");
                System.out.println("Specific error message");
                System.out.println(e.getMessage());
                System.out.println("\n\nEnter them again...\n");
            }
        } while(unsuccessfulAttack);

        return coordinate;
    }

    private int counter = -1;
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
}
