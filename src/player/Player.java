package player;

import grid.Ocean;
import grid.Target;
import ship.Ship;
import utility.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Player abstract with the methods to be used for human and cpu
 */
public abstract class Player {
    //each player consists of an ocean grid
    private Ocean ocean;
    //each player consists of a target grid
    private Target target;
    //each player consists of a fleet of ships
    private ArrayList<Ship> ships;

    public Player(){
        this.ocean = new Ocean();
        this.target = new Target();
        this.ships = new ArrayList<>();
    }

    /**
     * get the ocean of the user (only possible in this package)
     * @return the users ocean
     */
    protected Ocean getOcean() {
        return ocean;
    }

    /**
     * get the target of the user (only possible in this package)
     * @return the users target
     */
    protected Target getTarget() {
        return target;
    }

    /**
     * add a ship to the current fleet
     * @param ship to be stored in the fleet (ships)
     */
    protected void addShip(Ship ship){
        ships.add(ship);
    }

    /**
     * check if the users fleet is destroyed
     * @return true or false based on if the fleet is destroyed
     */
    public boolean isFleetDestroyed(){
        AtomicInteger health = new AtomicInteger();
        ships.forEach( (ship) -> { health.addAndGet(ship.getHealth()); } );
        if (health.get() > 0){
            return false;
        } else {
            return true;
        }
    }

    /**
     * universal method for a player who's under attack
     * by not passing the user the enemy cannot sneak peak into the users fleet
     * @param coordinate coordinate where the attack is located
     * @return the coordinate state of the item under attack
     */
    public CoordinateState underAttack(Coordinate coordinate){
        if (ocean.getGridValue(coordinate).getState() instanceof Occupied) {
            Ship s = getShipFromCoordinate(coordinate);
            //deduct ship health
            s.shipGotHit();
            coordinate.setState(Hit.state());
            if (s.getHealth() == 0){
                return new Sunk(s.getShipType());
            } else {
                return Hit.state();
            }

        }
        coordinate.setState(Missed.state());
        return Missed.state();
    }

    /**
     * check if the ship under attack was destroyed or still has some life left
     * @param c coordinate under attack
     * @return if the underlying ship is sunk or still alive
     */
    public boolean didShipSink(Coordinate c){
        if (c.getState() instanceof Sunk) return true;
        return false;
    }

    /**
     * if the ship sunk, inform about all the coordinates which are now sunk
     * @param c coordinate under attack
     * @return an arraylist of all the coordinates to be changed from hit to sunk
     */
    public ArrayList informAboutSunkenShip(Coordinate c){
        Ship s = getShipFromCoordinate(c);
        return s.getPlacement();
    }

    /**
     * update the target grid and change the state of the attacker coordinate
     * @param coordinate attacked
     */
    public void updateTarget(Coordinate coordinate){
        target.updateTarget(coordinate);
    }

    /**
     * Get the ship from the underlying coordinate
     * @param c coordiante in question
     * @return the underlying ship
     */
    public Ship getShipFromCoordinate(Coordinate c){
        for(Ship s : ships){
            for(Coordinate shipCord : s.getPlacement()){
                if(shipCord.getX() == c.getX() && shipCord.getY() == c.getY()){
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * empty method to display that each player has this method
     * Implementation will take place in each associated player
     * @return null
     */
    public Coordinate attack(){return null;}
    /**
     * empty method to display that each player has this method
     * Implementation will take place in each associated player
     */
    public void shipPlacement(){}

    /**
     * draw the target grid of the user
     */
    public void drawTarget(){
        this.target.printGrid();
    }

    /**
     * draw the ocean grid of the user
     */
    public void drawOcean(){
        this.ocean.printGrid();
    }

    /**
     * draw the final grid (target grid of the winner and ocean grid of the winner with the enemy attacks)
     * @param o winner
     * @param t loser
     */
    public void drawFinal(Player o, Player t){
        t.target.printGrid();
        this.ocean.drawFinal(t.getOcean(),o.getTarget());
    }

    /**
     * draw the game flow -> target and then ocean with a line inbetween
     */
    public void drawGameFlow(){
        drawTarget();
        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("");
        drawOcean();
    }
}
