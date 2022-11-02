package player;

import utility.*;
import ship.Ship;
import ship.ShipType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Human extends Player {

    public Human(){

    }
    private final HashMap<ShipType, CoordinateState> SHIP_STATE_MAPPING = new HashMap<ShipType, CoordinateState>() {{
        put(ship.ShipType.CARRIER, utility.OccupiedCarrier.state());
        put(ShipType.BATTLESHIP, utility.OccupiedBattleship.state());
        put(ShipType.PATROL_BOAT, utility.OccupiedPatrol.state());
        put(ShipType.SUBMARINE, utility.OccupiedSubmarine.state());
    }};

    private final HashMap<CoordinateState, ShipType> STATE_SHIP_MAPPING = new HashMap<CoordinateState, ShipType>() {{
        put(utility.OccupiedCarrier.state(), ship.ShipType.CARRIER);
        put(utility.OccupiedBattleship.state(), ShipType.BATTLESHIP);
        put(utility.OccupiedPatrol.state(), ShipType.PATROL_BOAT);
        put(utility.OccupiedSubmarine.state(), ShipType.SUBMARINE);
    }};

    //map with shiplengths

    //map with shiptype

    public void setShips() {
        for (ShipType shipType : ShipType.values()){
            for(int shipFromType = 0; shipFromType < shipType.getNumberOfShips(); shipFromType++){
                System.out.println("Number of Ships:" + shipType.getAbbreviation() + shipType.getNumberOfShips());
                boolean entered_unsuccessfully = true;
                do {
                    System.out.println("Where do you want to place your " + shipType + " (size: " + shipType.getShipLength() + ") (Ship no. " + (shipFromType + 1) + "/ " + shipType.getNumberOfShips() + ")\n" +
                            "Enter Start end End Coordinates (e.g. A1,A" + shipType.getShipLength() + ")");
                    try {
                        //String[] coords = new Scanner(System.in).next().split(",");
                        //MOCK
                        String[] coords = new Scanner(mockShipPlacement()).next().split(",");
                        Coordinate start = new Coordinate(coords[0], Occupied.state());
                        Coordinate end = new Coordinate(coords[1], Occupied.state());
                        // check ship placement
                        if (this.getOcean().placeShip(start, end)){ //there might be some error in placeShip
                            Ship ship = new Ship(start, end, shipType);
                            System.out.println("Ship Length:" + ship.getShipType().getShipLength());
                            if(end.getX() > start.getX()){
                                for (int x = start.getX(); x < start.getX() + ship.getShipType().getShipLength(); x++){
                                    this.getOcean().setFieldState(x, end.getY(), SHIP_STATE_MAPPING.get(shipType));
                                }
                            } else {
                                for (int y = start.getY(); y < start.getY() + ship.getShipType().getShipLength(); y++){
                                    this.getOcean().setFieldState(end.getX(), y, SHIP_STATE_MAPPING.get(shipType));
                                }
                            }
                            addShip(ship);
                            entered_unsuccessfully = false;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("\n\nEnter them again...\n");
                    }

                }while(entered_unsuccessfully);
                //this.getOcean().printGrid();
            }
        }
        this.getOcean().printGrid();
    }

    /*public Coordinate attack(Computer enemy) {
        boolean unsuccessfulAttack = true;
        Coordinate coordinate = null;
        do {
            System.out.println("Attack attack attack, Captain enter the coordinates");
            try {
                coordinate = new Coordinate(new Scanner(System.in).next(), Empty.state());
                //this.getTarget().shipAttack(coordinate, enemy);
                unsuccessfulAttack = false;
            } catch (Exception e) {
                System.out.println("\nPlease enter valid coordinates...\n\n");
                System.out.println("Specific error message");
                System.out.println(e.getMessage());
                System.out.println("\n\nEnter them again...\n");
            }
        } while(unsuccessfulAttack);

        return coordinate;
    }*/

    public void attack(Computer enemy) {
        System.out.println("Enter Coordinates:");
        Random random = new Random();
        int x;
        int y;
        Coordinate coordinate = null;
        boolean unsuccessfulAttack = true;
        do {
            try {
                CoordinateValue coordinateValue = utility.IO.getCoordinates(new Scanner(System.in).next());

                //check if we already shoot at this place in target grid
                if (this.getTarget().getGridValue(coordinateValue.x, coordinateValue.y).getState() == utility.Missed.state() || this.getTarget().getGridValue(coordinateValue.x, coordinateValue.y).getState() == utility.Hit.state())
                    throw new Exception("Already attacked!");
                //check if there is a boat there and which one
                if (enemy.getOcean().getGridValue(coordinateValue.x, coordinateValue.y).getState() != utility.Empty.state()) {
                    this.getTarget().setFieldState(coordinateValue.x, coordinateValue.y, utility.Hit.state());
                    //check if we hit all fields from a boat
                    //compare your hits with the boat at enemy target
                    /*if(enemy.getOcean().getGridValue(coordinateValue.x, coordinateValue.y+1).getState() != utility.Empty.state()){ //vertical
                        //get shipType from state at grid pos
                        ShipType shipType = STATE_SHIP_MAPPING.get(enemy.getOcean().getGridValue(coordinateValue.x, coordinateValue.y+1).getState());
                        //get shipLength from shiptype
                        int count = 0;
                        for (int i=0; i<shipType.getShipLength(); ++i){
                            if()
                             count++;
                        }
                        if(count == shipType.getShipLength()){

                        }
                    }
                    else{ //horizontal

                    }*/
                }
                else
                    this.getTarget().setFieldState(coordinateValue.x, coordinateValue.y,utility.Missed.state());
                unsuccessfulAttack = false;
            } catch (Exception e) {
                //coordinates were already attacked or anything. we do not want to write an output to not spam the user
                System.out.println("Already attacked! Enter different Coordinates:");
            }
        } while(unsuccessfulAttack);
        this.getTarget().printGrid();
    }

    public void ShipSunk(){
        //check enemy target grid and compare

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
