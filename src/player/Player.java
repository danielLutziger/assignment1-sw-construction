package player;

import game.Coordinates;
import game.CoordinatesOutOfBoundsException;
import grid.Ocean;
import grid.Target;
import ship.Ship;
import ship.ShipType;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private boolean ai;
    private Ocean ocean;
    private Target target;

    private ArrayList<Ship> ships;

    public Player(){
        this.ai = false;
        this.ocean = new Ocean();
        this.target = new Target();
    }

    //constructor to create either a user or an AI
    public Player(boolean ai){
        this.ocean = new Ocean();
        this.target = new Target();
        this.ai = ai;
    }

    public Ocean getOcean() {
        return ocean;
    }

    /*
    TODO: DELETE THIS METHOD ONCE AI SHIP PLACEMENT IS COMPLETED!
     */
    public void setOcean(Ocean ocean) {
        this.ocean = ocean;
    }

    public Target getTarget() {
        return target;
    }

    public void setShips() {
        for (ShipType shipType : ShipType.values()){
            for(int shipFromType = 0; shipFromType < shipType.getNumberOfShips(); shipFromType++){
                boolean entered_unsuccessfully;
                do {
                    System.out.println("Where do you want to place your " + shipType + " (size: " + shipType.getShipLength() + ") (Ship no. " + (shipFromType + 1) + "/ " + shipType.getNumberOfShips() + ")\n" +
                            "Enter Start end End Coordinates (e.g. A1,A" + shipType.getShipLength() + ")");
                    try {
                        Coordinates coordinates = new Coordinates(new Scanner(System.in).next().split(","));
                        Ship ship = new Ship(coordinates, shipType);
                        this.ocean.placeShip(ship); //placement validation still required! boats can be stacked over each other
                        entered_unsuccessfully = false;
                    } catch (Exception e) {
                        System.out.println("\nPlease enter valid coordinates...\n\n");
                        System.out.println("Specific error message");
                        System.out.println(e.getMessage());
                        System.out.println("\n\nEnter them again...\n");
                        entered_unsuccessfully = true;
                    }

                }while(entered_unsuccessfully);
                this.ocean.printGrid();
            }
        }
    }

    public void attack() {
        boolean unsuccessfullAttack = true;
        do {
            try {
                Coordinates coordinate = new Coordinates(new Scanner(System.in).next());
                unsuccessfullAttack = false;
            } catch (Exception e) {
                System.out.println("\nPlease enter valid coordinates...\n\n");
                System.out.println("Specific error message");
                System.out.println(e.getMessage());
                System.out.println("\n\nEnter them again...\n");
            }
        } while(unsuccessfullAttack);
    }
}
