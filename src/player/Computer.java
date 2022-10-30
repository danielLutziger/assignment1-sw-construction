package player;

import game.Coordinate;
import game.Coordinates;
import game.CoordinatesOutOfBoundsException;
import grid.Ocean;
import grid.ShipPlacementCollisionException;
import grid.Target;
import ship.Ship;
import ship.ShipDirectionNotValidException;
import ship.ShipType;

import java.util.*;

public class Computer extends Player{
    private boolean ai;
    private Ocean ocean;
    private Target target;

    private ArrayList<Ship> ships;
    HashMap<Integer, Character> COORDINATE_LETTERS = new HashMap<Integer, Character>() {{
        put(0, 'A');
        put(1, 'B');
        put(2, 'C');
        put(3, 'D');
        put(4, 'E');
        put(5, 'F');
        put(6, 'G');
        put(7, 'H');
        put(8, 'I');
        put(9, 'J');
    }};

    int GRID_SIZE;

    public Computer() {
        this.ocean = new Ocean();
        this.target = new Target();
        this.ai = ai;
        this.ships = new ArrayList<>();
        GRID_SIZE = ocean.getGridSize();
    }




    public void setShips() {
        Random random = new Random();
        for (ShipType shipType : ShipType.values()){
            for(int shipFromType = 0; shipFromType < shipType.getNumberOfShips(); shipFromType++){
                boolean placed_unsuccessfully;
                do {
                    System.out.println("Ai places " + shipType + " (size: " + shipType.getShipLength() + ") (Ship no. " + (shipFromType + 1) + "/ " + shipType.getNumberOfShips() + ")");
                    try {
                        //System.out.println("Placing ship with length: " + i);
                        //start point of the ship and direction
                        boolean vertical = random.nextBoolean();
                        int x;
                        int y;
                        if(vertical) {
                            x = random.nextInt(GRID_SIZE);
                            y = random.nextInt(GRID_SIZE - (shipType.getShipLength()-1));
                        }
                        else {
                            x = random.nextInt(GRID_SIZE - (shipType.getShipLength()-1));
                            y = random.nextInt(GRID_SIZE);
                        }


                        //check valid coordinates
                        assert(x <= 9);
                        assert(y <= 9);
                        assert(x + (shipType.getShipLength()-1) <= 9);
                        assert(y + (shipType.getShipLength()-1) <= 9);

                        char X = COORDINATE_LETTERS.get(x);
                        char X_end_v = COORDINATE_LETTERS.get(x + (shipType.getShipLength()-1));

                        String start = new StringBuilder().append(X).append(y).toString();
                        Coordinates coordinates;
                        if (vertical) {
                            String end_vertical = new StringBuilder().append(X_end_v).append(y).toString();
                            String[] coordinateInputVert = {start, end_vertical};
                            coordinates = new Coordinates(coordinateInputVert);
                        }
                        else{
                            String end_horizontal = new StringBuilder().append(X).append(y + (shipType.getShipLength()-1)).toString();
                            String[] coordinateInputHoriz = {start, end_horizontal};
                            coordinates = new Coordinates(coordinateInputHoriz);
                        }
                        Ship ship = new Ship(coordinates, shipType);
                        ocean.placeShip(ship);
                        ships.add(ship);

                        placed_unsuccessfully = false;
                        System.out.println("placement successful");
                    } catch (Exception e) {
                        placed_unsuccessfully = true;
                        System.out.println("placement failed");
                    }
                }while(placed_unsuccessfully);
                this.ocean.printGrid();
            }
        }
    }

    public void attack(Player enemy) {
        Random random = new Random();
        boolean unsuccessfulAttack = true;
        do {
            System.out.println("Attack attack attack, Captain enter the coordinates");
            try {
                int x = random.nextInt(GRID_SIZE);
                int y = random.nextInt(GRID_SIZE);
                char X = COORDINATE_LETTERS.get(x);
                String start = new StringBuilder().append(X).append(y).toString();
                String[] coordinateInput = {start};
                Coordinates coordinate = new Coordinates(coordinateInput);
                target.shipAttack(coordinate, enemy);
                unsuccessfulAttack = false;
            } catch (Exception e) {
                System.out.println("\nPlease enter valid coordinates...\n\n");
                System.out.println("Specific error message");
                System.out.println(e.getMessage());
                System.out.println("\n\nEnter them again...\n");
            }
        } while(unsuccessfulAttack);
        this.target.printGrid();


        //chose random number in x and y from 0-9
        //create a coordinate
        //check if at this number already an attack was executed
        //if not shoot
        //shipAttack method does both
        // catch exception from ship attack
        // handle ship already attacked exception
        // with new random number generation
    }
}
