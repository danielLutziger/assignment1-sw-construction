package grid;

import ship.Ship;

/**
 * the grid for the ocean showing the attacks
 */
public class Ocean extends Grid{

    /**
     * @param ship: taking in the ship which has to be placed
     * @return will return the new (with ship placement) grid
     * @throws ShipPlacementCollisionException : will throw an exception if the placement was not successful, placement will in this case not take over the grid
     */
    public char[][] placeShip(Ship ship) {

        ship.getStart()

        char[][] localGrid = getGrid();
        for(int coordinate_values = 0; coordinate_values < ship.getShipType().getShipLength(); coordinate_values++){
            if (ship.getShipDirection() == ShipDirection.HORIZONTAL && isCoordinateAvailableForShipPlacement(getGrid()[ship.getCoordinates().getStart().getX()][ship.getCoordinates().getStart().getY()+coordinate_values])){
                localGrid[ship.getCoordinates().getStart().getX()][ship.getCoordinates().getStart().getY()+coordinate_values] = ship.getShipType().getAbbreviation();
            }
            else if (ship.getShipDirection() == ShipDirection.VERTICAL && isCoordinateAvailableForShipPlacement(getGrid()[ship.getCoordinates().getStart().getX()+coordinate_values][ship.getCoordinates().getStart().getY()])){
                localGrid[ship.getCoordinates().getStart().getX()+coordinate_values][ship.getCoordinates().getStart().getY()] = ship.getShipType().getAbbreviation();
            }
            else {
                throw new ShipPlacementCollisionException("Ship placement was not successful. Please enter correct coordinates.");
            }
        }
        //only overwrite grid if no exception is thrown => if the placement is valid
        setGrid(localGrid);
        return getGrid();
    }

    /**
     * @param placeOnGrid the content of the element where the element should be placed
     * @return if the element is available
     * @throws ShipPlacementCollisionException : in case the element is not available
     */
    public boolean isCoordinateAvailableForShipPlacement(char placeOnGrid) throws ShipPlacementCollisionException {
        if (placeOnGrid == INIT_FILLER_VALUE){
            return true;
        } else {
            throw new ShipPlacementCollisionException("Ship placement is not valid, another ship is already there!");
        }
    }
}
