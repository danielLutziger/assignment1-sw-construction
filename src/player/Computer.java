package player;

import utility.Coordinate;
import ship.Ship;
import ship.ShipType;
import utility.Occupied;

import java.util.Random;

public class Computer extends Player {

    public Computer() {
    }

    public void setShips() {
        for (ShipType shipType : ShipType.values()) {
            // generate horizontal / vertical placement
            Random rand = new Random();
            int direction = rand.nextInt(2);
            for (int shipFromType = 0; shipFromType < shipType.getNumberOfShips(); shipFromType++) {
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
                        Coordinate start = new Coordinate(randomX, randomY, new Occupied(shipType));
                        Coordinate end = new Coordinate(randomX, randomY, new Occupied(shipType));
                        // check ship placement
                        if (this.getOcean().placeShip(start, end)){
                            Ship ship = new Ship(start, end, shipType);
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
        boolean unsuccessfulAttack = true;
        do {

            try {
                int x = random.nextInt(getTarget().getGridSize());
                int y = random.nextInt(getTarget().getGridSize());
                //Coordinates coordinates = new Coordinates(x, y);
                //Coordinates coordinates = new Coordinates(x, y);
                //getTarget().shipAttack(coordinates, enemy);
                unsuccessfulAttack = false;
            } catch (Exception e) {
                //coordinates were already attacked or anything. we do not want to write an output to not spam the user
            }
            //TODO check if we hit something on target grid and display this
        } while(unsuccessfulAttack);
        //getTarget().printGrid();
    }
}
