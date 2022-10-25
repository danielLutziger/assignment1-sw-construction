package ship;

public class ShipDirectionNotValidException extends Exception{
    public ShipDirectionNotValidException(String errorMessage) {
        super(errorMessage);
    }
}