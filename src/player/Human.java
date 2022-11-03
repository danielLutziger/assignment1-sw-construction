package player;

import utility.*;
import ship.Ship;
import ship.ShipType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

public class Human extends Player {

    public Human(){

    }
    ArrayList<Ship> ships = new ArrayList<Ship>();

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
                        if (this.getOcean().placeShip(start, end)){ //there might be some error in placeShip they can overlap
                            Ship ship = new Ship(start, end, shipType);
                            ships.add(ship);
                            //System.out.println("Ship Length:" + ship.getShipType().getShipLength());
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
        System.out.println("Human Ocean:\n");
        this.getOcean().printGrid();
    }

    public boolean attack(Computer enemy) {
        System.out.println("Enter Coordinates:");
        Random random = new Random();
        int x;
        int y;
        Coordinate coordinate = null;
        boolean unsuccessfulAttack = true;
        do {
            try {
                CoordinateValue coordinateValue = utility.IO.getCoordinates(new Scanner(System.in).next());
                //check if already hit
                if (this.getTarget().getGridValue(coordinateValue.x, coordinateValue.y).getState() != utility.Empty.state())
                    throw new Exception("Already attacked!");
                //check for a hit
                if (enemy.getOcean().getGridValue(coordinateValue.x, coordinateValue.y).getState() != utility.Empty.state()) {
                    this.getTarget().setFieldState(coordinateValue.x, coordinateValue.y, utility.Hit.state());
                    //check if we hit all fields from a boat
                    this.checkShipSunk(enemy);
                } else
                    this.getTarget().setFieldState(coordinateValue.x, coordinateValue.y, utility.Missed.state());
                unsuccessfulAttack = false;
            } catch (Exception e) {
                System.out.println("Bad coordinates or already attacked! Enter different Coordinates:");
            }
        } while (unsuccessfulAttack);
        System.out.println("Target Grid after Attack:");
        this.getTarget().printGrid();
        if (isGameEnd(enemy)) {
            System.out.println("Human defeated the Computer");
            return true;
        }
        return false;
    }



    //check if we hit all fields from a boat and if so set state to occupiedShipType
    public void checkShipSunk(Computer enemy){
        Iterator<Ship> shipIterator = enemy.ships.iterator();
        for(int i=0; i < ships.size(); ++i) {
            Ship currentShip = shipIterator.next();
            //System.out.println("ShipType: " + currentShip.getShipType());
            Coordinate start = currentShip.getStart();
            Coordinate end = currentShip.getEnd();
            boolean fullShipHit = true;
            if (end.getX() > start.getX()) { //horizontal
                for (int x_ship = start.getX(); x_ship <= end.getX();) {
                    if (this.getTarget().getGridValue(x_ship, start.getY()).getState() != utility.Hit.state()) {
                        fullShipHit = false;
                        break;
                    } else {
                        System.out.println("Hit recognized on: " + currentShip.getShipType() + " Hit at: " + x_ship + start.getY());
                    }
                    x_ship++;
                }
            } else {//vertical
                for (int y_ship = start.getY(); y_ship <= end.getY();) {
                    if (this.getTarget().getGridValue(start.getX(), y_ship).getState() != utility.Hit.state()) {
                        fullShipHit = false;
                        break;
                    } else {
                        System.out.println("Hit recognized on: " + currentShip.getShipType() + " Hit at: " + start.getX() + y_ship);
                    }
                    y_ship++;
                }
            }
            if (fullShipHit) {
                if (end.getX() > start.getX()) {
                    for (int x_ship = start.getX(); x_ship <= end.getX(); x_ship++) {
                        this.getTarget().setFieldState(x_ship, start.getY(), SHIP_STATE_MAPPING.get(currentShip.getShipType()));
                    }
                } else {
                    for (int y_ship = start.getY(); y_ship <= end.getY(); y_ship++) {
                        this.getTarget().setFieldState(start.getX(), y_ship, SHIP_STATE_MAPPING.get(currentShip.getShipType()));
                    }
                }
                currentShip.setSunk();
            }
        }
    }

    private boolean isGameEnd(Computer enemy) {
        boolean gameEnd = true;
        Iterator<Ship> shipIterator = enemy.ships.iterator();
        for(int i=0; i < ships.size(); ++i) {
            Ship currentShip = shipIterator.next();
            if (currentShip.getSunk() != true) {
                gameEnd =  false;
            }
        }
        return gameEnd;
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
