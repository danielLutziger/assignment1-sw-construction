package grid;

import ship.Ship;
import utility.Coordinate;
import utility.Empty;
import utility.Occupied;

/**
 * the grid for the ocean showing the attacks
 */
public class Ocean extends Grid {

    public Ocean(){
        setName("OCEAN");
    }

    /**
     * Method to check if the placement for the ship is possible
     * @param start: start coordinate for the ship
     * @param end: end coordinate for the ship
     * @return if the coordinates where the ship will be placed can be set (true => possible, false => not possible)
     */
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

    /**
     * Update the ocean by changing the states of the underlying coordinates
     * @param ship which will update the state
     */
    public void updateOcean(Ship ship){
        //addShipToFleet
        Coordinate[][] grid = getGrid();

        //check if the ship is horizontal or vertical
        if(ship.getEnd().getX() > ship.getStart().getX()){
            for (int x = ship.getStart().getX(); x <= ship.getEnd().getX(); x++){
                //set the state of the coordinates of the object to occupied
                grid[x][ship.getStart().getY()].setState(new Occupied(ship.getShipType()));
            }
        } else {
            for (int y = ship.getStart().getY(); y <= ship.getEnd().getY(); y++){
                //set the state of the coordinates of the object to occupied
                grid[ship.getStart().getX()][y].setState(new Occupied(ship.getShipType()));
            }
        }
        this.setGrid(grid);
    }

}
