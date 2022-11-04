package grid;

import utility.Coordinate;
import utility.Empty;

/**
 * the grid for the targets showing the own placements
 */
public class Target extends Grid{
    public Target(){
        setName("TARGET");
    }

    /**
     * Change the state of the underlying coordinate
     * @param coordinate which will change it state
     */
    public void updateTarget(Coordinate coordinate){
        //get the grid coordinate reference
        //update the grid reference to the state of the passed coordinate
        this.getGridValue(coordinate).setState(coordinate.getState());
    }

    /**
     * Method to check if the target is attackable or not (e.g. already attacked)
     * @param coordinate which will be attacked
     * @return if the coordinate is attackable (true) or not (false)
     */
    public boolean isTargetAttackable(Coordinate coordinate){
        if (this.getGridValue(coordinate).getState() instanceof Empty) return true;
        return false;
    }
}
