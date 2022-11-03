package player;

import grid.Ocean;
import grid.Target;
import utility.Coordinate;
import ship.Ship;
import ship.ShipType;
import utility.CoordinateState;
import utility.Empty;
import utility.Occupied;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Computer extends Player {

    public Computer() {

    }

    ArrayList<Ship> ships;

    private final HashMap<ShipType, CoordinateState> SHIP_STATE_MAPPING = new HashMap<ShipType, CoordinateState>() {{
        put(ship.ShipType.CARRIER, utility.OccupiedCarrier.state());
        put(ShipType.BATTLESHIP, utility.OccupiedBattleship.state());
        put(ShipType.PATROL_BOAT, utility.OccupiedPatrol.state());
        put(ShipType.SUBMARINE, utility.OccupiedSubmarine.state());
    }};

    public void setShips() {
        for (ShipType shipType : ShipType.values()) {
            // generate horizontal / vertical placement
            Random rand = new Random();
            int direction = rand.nextInt(2);
            for (int shipFromType = 0; shipFromType < shipType.getNumberOfShips(); shipFromType++) {
                System.out.println("Number of Ships:" + shipType.getAbbreviation() + shipType.getNumberOfShips());
                boolean entered_unsuccessfully = true;
                do {
                    try {
                        //generate random value
                        int randomY = 0;
                        int randomX = 0;
                        if (direction == 1) {
                            //horizontal
                            randomX = rand.nextInt(getTarget().getGridSize() - shipType.getShipLength());
                            randomY = rand.nextInt(getTarget().getGridSize());
                        } else {
                            //vertical
                            randomY = rand.nextInt(getTarget().getGridSize() - shipType.getShipLength());
                            randomX = rand.nextInt(getTarget().getGridSize());
                        }
                        Coordinate start = new Coordinate(randomX, randomY, Occupied.state());
                        Coordinate end = new Coordinate(randomX, randomY, Occupied.state());
                        // check ship placement
                        if (this.getOcean().placeShip(start, end)){
                            Ship ship = new Ship(start, end, shipType); //use shiptype as states
                            ships.add(ship);
                            System.out.println("Ship Length:" + ship.getShipType().getShipLength());
                            if(end.getX() > start.getX()){
                                for (int x = randomX; x < randomX + ship.getShipType().getShipLength(); x++){
                                    this.getOcean().setFieldState(x, end.getY(), SHIP_STATE_MAPPING.get(shipType));
                                }
                            } else {
                                for (int y = randomY; y < randomY + ship.getShipType().getShipLength(); y++){
                                    this.getOcean().setFieldState(end.getX(), y, SHIP_STATE_MAPPING.get(shipType));
                                }
                            }
                            addShip(ship);
                            entered_unsuccessfully = false;
                        }
                    } catch (Exception e) {
                        entered_unsuccessfully = true;
                    }

                } while (entered_unsuccessfully);
            }
        }
        this.getOcean().printGrid();
    }

    public void attack(Human enemy) {
        System.out.println("Opponent enters coordinates");
        Random random = new Random();
        int x;
        int y;
        boolean unsuccessfulAttack = true;
        do {
            try {
                x = random.nextInt(this.getTarget().getGridSize());
                y = random.nextInt(this.getTarget().getGridSize());

                //check if we already shoot at this place in target grid
                if (this.getTarget().getGridValue(x, y).getState() != utility.Empty.state())
                    throw new Exception("Already attacked!");
                //check if there is a boat there and which one
                if (enemy.getOcean().getGridValue(x, y).getState() == utility.Occupied.state())
                    enemy.getOcean().setFieldState(x,y,utility.Hit.state());
                unsuccessfulAttack = false;
            } catch (Exception e) {
                //coordinates were already attacked or anything. we do not want to write an output to not spam the user
            }
        } while(unsuccessfulAttack);
        System.out.println("Opponent Ocean:\n");
        getOcean().printGrid(); //dont show computer grid
    }
}
