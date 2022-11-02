package grid;

import ship.Ship;
import utility.Coordinate;
import utility.CoordinateState;
import utility.Empty;
import utility.Occupied;

/**
 * the grid for the ocean showing the attacks
 */
public class Ocean extends Grid{

    private final int size = 10;
    private Coordinate[][] grid;


    //no constructor
    //constructor sets the coordinate states
    public Ocean(){
        grid = new Coordinate[size][size];
        for (int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                grid[row][column] = new Coordinate(row, column);
            }
        }
    }
    //use hit.updateState()

    public boolean placeShip(Coordinate start, Coordinate end) {
        //check if vertical or horizontal
        //iterate through ship and check if all states are empty
        //check if the coordinate state is empty
        if(end.getX() > start.getX()){
            for (int x = start.getX(); x <= end.getX(); x++){
                if (!(getGrid()[x][start.getY()].getState() instanceof Empty)){
                    return false;
                }
            }
        } else {
            for (int y = start.getY(); y <= end.getY(); y++){
                if (!(getGrid()[start.getX()][y].getState() instanceof Empty)){
                    return false;
                }
            }
        }
        return true;
    }

    public void updateOcean(Ship ship){
        //addShipToFleet
        Coordinate[][] grid = getGrid();

        if(ship.getEnd().getX() > ship.getStart().getX()){
            for (int x = ship.getStart().getX()+1; x < ship.getEnd().getX(); x++){
                grid[x][ship.getStart().getY()].setState(Occupied.state());
            }
        } else {
            for (int y = ship.getStart().getY()+1; y < ship.getEnd().getY(); y++){
                grid[ship.getStart().getX()][y].setState(Occupied.state());
            }
        }
        this.setGrid(grid);
    }
}
