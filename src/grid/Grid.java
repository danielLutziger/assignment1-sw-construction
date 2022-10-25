package grid;

import game.Coordinates;
import ship.Ship;
import ship.ShipDirection;

public abstract class Grid {
    private final int size = 10;
    private char[][] grid;

    public Grid(){
        grid = new char[this.size][this.size];
        //fill Grid with 'x'
        for (int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                grid[row][column] = 'X';
            }
        }
    }

    public char[][] placeShip(Ship ship) {
        for(int coordinate_values = 0; coordinate_values < ship.getShipType().getShipLength(); coordinate_values++){
            if (ship.getShipDirection() == ShipDirection.HORIZONTAL){
                this.grid[ship.getCoordinates().getStart().getX()][ship.getCoordinates().getStart().getY()+coordinate_values] = '0';
            }
            else {
                this.grid[ship.getCoordinates().getStart().getX()+coordinate_values][ship.getCoordinates().getStart().getY()] = '0';
            }
        }
        return this.grid;
    }

    public void printGrid(){
        for (int row = 0; row < size; row++){
            System.out.println(grid[row]);
        }
    }
}
