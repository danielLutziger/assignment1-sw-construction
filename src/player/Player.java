package player;

import game.Coordinates;
import game.CoordinatesOutOfBoundsException;
import grid.Ocean;
import grid.Target;
import ship.Ship;
import ship.ShipDirectionNotValidException;
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
        //this.target = new Target();
    }

    //constructor to create either a user or an AI
    public Player(boolean ai){
        this.ocean = new Ocean();
        //this.target = new Target();
        this.ai = ai;
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
                            "Enter Start end End Coordinates (e.g. A0,A" + shipType.getShipLength() + ")");
                    try {
                        Coordinates coordinates = new Coordinates(new Scanner(System.in).next());
                        Ship ship = new Ship(coordinates, shipType);
                        ocean.placeShip(ship);
                        entered_unsuccessfully = false;
                    } catch (Exception e) {
                        System.out.println("Please enter valid coordinates...");
                        entered_unsuccessfully = true;
                    }

                }while(entered_unsuccessfully);
                this.ocean.printGrid();
            }
        }
    }
}
