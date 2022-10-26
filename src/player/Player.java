package player;

import game.Coordinates;
import grid.Ocean;
import grid.Target;
import ship.Ship;
import ship.ShipType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
        this.ships = new ArrayList<>();
    }

    //constructor to create either a user or an AI
    public Player(boolean ai){
        this.ocean = new Ocean();
        this.target = new Target();
        this.ai = ai;
        this.ships = new ArrayList<>();
    }

    public Ocean getOcean() {
        return ocean;
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
                        //Coordinates coordinates = new Coordinates(new Scanner(System.in).next().split(","));
                        //MOCK
                        Coordinates coordinates = new Coordinates(new Scanner(mockShipPlacement()).next().split(","));
                        Ship ship = new Ship(coordinates, shipType);
                        ocean.placeShip(ship); //placement validation still required! boats can be stacked over each other
                        ships.add(ship);
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

    private int counter = -1;
    private InputStream mockShipPlacement(){
        counter++;
        String[] pm = {"E9,J9",
        "G0,J0",
        "A2,A5",
        "D0,D2",
        "F2,H2",
        "J5,J7",
        "A0,B0",
        "A7,B7",
        "F5,F6",
        "J2,J3"};
        InputStream in = new ByteArrayInputStream(pm[counter].getBytes());
        return in;
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
    }

    public ArrayList<Ship> getShips(){
        return ships;
    }
}
