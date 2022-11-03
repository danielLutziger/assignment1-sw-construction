package player;

import utility.Coordinate;
import ship.Ship;
import ship.ShipType;
import utility.Empty;
import utility.Occupied;

import java.util.Random;
import java.util.Scanner;

public class Computer extends Player {

    public Computer() {
    }

    public void shipPlacement() {
        for (ShipType shipType : ShipType.values()) {
            // generate horizontal / vertical placement
            Random rand = new Random();
            int direction = rand.nextInt(2);
            for (int shipFromType = 0; shipFromType < shipType.getNumberOfShips(); shipFromType++) {
                boolean entered_unsuccessfully = true;
                do {
                    try {
                        //generate rando
                        Coordinate start;
                        Coordinate end;
                        if (direction == 1) {
                            //horizontal
                            int randomX = rand.nextInt(getTarget().getGridSize() - shipType.getShipLength());
                            int randomY = rand.nextInt(getTarget().getGridSize());
                            start = new Coordinate(randomX, randomY, new Occupied(shipType));
                            end = new Coordinate(randomX + shipType.getShipLength()-1, randomY, new Occupied(shipType));
                        } else {
                            //vertical
                            int randomX = rand.nextInt(getTarget().getGridSize());
                            int randomY = rand.nextInt(getTarget().getGridSize() - shipType.getShipLength());
                            start = new Coordinate(randomX, randomY, new Occupied(shipType));
                            end = new Coordinate(randomX, randomY + shipType.getShipLength()-1, new Occupied(shipType));
                        }
                        // check ship placement
                        if (this.getOcean().placeShip(start, end)){
                            try{
                                Ship ship = new Ship(start, end, shipType);
                                addShip(ship);
                                entered_unsuccessfully = false;
                                getOcean().updateOcean(ship);
                            }catch(AssertionError e){
                                entered_unsuccessfully = true;
                            }
                        }
                    } catch (Exception e) {
                        entered_unsuccessfully = true;
                    }

                } while (entered_unsuccessfully);
            }
        }
        this.getOcean().printGrid();
    }

    @Override
    public Coordinate attack() {
        boolean unsuccessfulAttack = true;
        Coordinate coordinate = null;
        Random rand = new Random();
        do {
            System.out.println("Attack attack attack, Captain enter the coordinates");
            try {
                int randomX = rand.nextInt(getTarget().getGridSize());
                int randomY = rand.nextInt(getTarget().getGridSize());
                coordinate = new Coordinate(randomX, randomY, Empty.state());
                if (getTarget().isTargetAttackable(coordinate)){
                    unsuccessfulAttack = false;
                }
            } catch (Exception e) {
            }
        } while(unsuccessfulAttack);

        return coordinate;
    }
}
