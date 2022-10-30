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
    HashMap<Character, Integer> COORDINATE_MAPPING = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('B', 1);
        put('C', 2);
        put('D', 3);
        put('E', 4);
        put('F', 5);
        put('G', 6);
        put('H', 7);
        put('I', 8);
        put('J', 9);
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
                        int x = random.nextInt(GRID_SIZE);
                        int y = random.nextInt(GRID_SIZE);
                        boolean vertical = random.nextBoolean();

                        String start = new StringBuilder().append(COORDINATE_MAPPING.get(x)).append((char)y).toString();
                        String end;
                        if (vertical) {
                            end = new StringBuilder().append(COORDINATE_MAPPING.get(x)).append(+ (char)y + (char)shipType.getShipLength()).toString();
                        }
                        else{
                            end = new StringBuilder().append(COORDINATE_MAPPING.get(x + shipType.getShipLength())).append(+ (char)y + (char)shipType.getShipLength()).toString();
                        }

                        Coordinates coordinates = new Coordinates(new StringBuilder().append(start).append(',').append(end).toString());
                        Ship ship = new Ship(coordinates, shipType);
                        ocean.placeShip(ship); //placement validation still required! boats can be stacked over each other
                        ships.add(ship);
                        placed_unsuccessfully = false;
                        System.out.println("placement successfull");
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
        boolean unsuccessfulAttack = true;
        do {
            System.out.println("Attack attack attack, Captain enter the coordinates");
            try {
                Coordinates coordinate = new Coordinates(new Scanner(System.in).next());
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
