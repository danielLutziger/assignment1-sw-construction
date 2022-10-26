package grid;

public class ShipAlreadyAttackedException  extends Exception{
    public ShipAlreadyAttackedException(String errorMessage) {
        super(errorMessage);
    }
}