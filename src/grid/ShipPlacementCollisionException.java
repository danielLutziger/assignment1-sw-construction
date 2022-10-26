package grid;

public class ShipPlacementCollisionException extends Exception{
    public ShipPlacementCollisionException(String errorMessage) {
        super(errorMessage);
    }
}
