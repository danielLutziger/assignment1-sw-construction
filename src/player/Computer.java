package player;

import game.Coordinates;
import ship.Ship;
import ship.ShipType;

import java.util.Random;
import java.util.Scanner;

public class Computer extends Player{

    public Computer(){}
    public void setShips(){
        for (ShipType shipType : ShipType.values()){
            // generate horizontal / vertical placement
            Random rand = new Random();
            int direction = rand.nextInt(2);
            for(int shipFromType = 0; shipFromType < shipType.getNumberOfShips(); shipFromType++){
                boolean entered_unsuccessfully;
                do {
                    try {
                        //generate random value
                        int randomY = 0;
                        int randomX = 0;
                        if (direction == 1){
                            //horizontal
                            randomX = rand.nextInt(10-shipType.getShipLength());
                            randomY = rand.nextInt(10);
                        }else {
                            //vertical
                            randomY = rand.nextInt(10-shipType.getShipLength());
                            randomX = rand.nextInt(10);
                        }
                        Coordinates coordinates = new Coordinates(direction, shipType.getShipLength(), randomX, randomY);
                        Ship ship = new Ship(coordinates, shipType);
                        getOcean().placeShip(ship); //placement validation still required! boats can be stacked over each other
                        addShip(ship);
                        entered_unsuccessfully = false;
                    } catch (Exception e) {
                        entered_unsuccessfully = true;
                    }

                }while(entered_unsuccessfully);
            }
        }
        this.getOcean().printGrid();
    }
}
