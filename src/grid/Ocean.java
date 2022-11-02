package grid;

import ship.Ship;
import utility.Coordinate;
import utility.Empty;

/**
 * the grid for the ocean showing the attacks
 */
public class Ocean extends Grid{

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
        for (Coordinate c : ship.getPlacement()){
            grid[c.getX()][c.getY()] = c;
        }
        this.setGrid(grid);
    }
}
