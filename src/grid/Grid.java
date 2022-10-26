package grid;

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

    /**
     * @param ship: taking in the ship which has to be placed
     * @return will return the new (with ship placement) grid
     * @throws ShipPlacementCollision: will throw an exception if the placement was not successful, placement will in this case not take over the grid
     */
    public char[][] placeShip(Ship ship) throws ShipPlacementCollision {
        char[][] localGrid = this.grid;
        for(int coordinate_values = 0; coordinate_values < ship.getShipType().getShipLength(); coordinate_values++){
            if (ship.getShipDirection() == ShipDirection.HORIZONTAL && isCoordinateAvailableForShipPlacement(this.grid[ship.getCoordinates().getStart().getX()][ship.getCoordinates().getStart().getY()+coordinate_values])){
                localGrid[ship.getCoordinates().getStart().getX()][ship.getCoordinates().getStart().getY()+coordinate_values] = ship.getShipType().getAbbreviation();
            }
            else if (ship.getShipDirection() == ShipDirection.VERTICAL && isCoordinateAvailableForShipPlacement(this.grid[ship.getCoordinates().getStart().getX()+coordinate_values][ship.getCoordinates().getStart().getY()])){
                localGrid[ship.getCoordinates().getStart().getX()+coordinate_values][ship.getCoordinates().getStart().getY()] = ship.getShipType().getAbbreviation();
            }
            else {
                throw new ShipPlacementCollision("Ship placement was not successful. Please enter correct coordinates.");
            }
        }
        //only overwrite grid if no exception is thrown => if the placement is valid
        setGrid(localGrid);
        return grid;
    }

    private void setGrid(char[][] grid){
        this.grid = grid;
    }

    public boolean isCoordinateAvailableForShipPlacement(char placeOnGrid) throws ShipPlacementCollision {
        if (placeOnGrid == 'X'){
            return true;
        } else {
            throw new ShipPlacementCollision("Ship placement is not valid, another ship is already there!");
        }
    }

    public void printGrid(){
        for (int row = 0; row < size; row++){
            System.out.println(grid[row]);
        }
    }
}
