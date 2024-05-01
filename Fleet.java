public class Fleet {
    // INSTANCE VARIABLES
    private Ship battleShip;
    private Ship aircraftCarrier;
    private Ship cruiser;
    private Ship sub;
    private Ship destroyer; 

    // CONSTRUCTORS
    /**
     * default constructor initializes all 
     * ship instance variables
     */
    public Fleet() {
        this.battleShip = new Battleship();
        this.aircraftCarrier = new AircraftCarrier();
        this.cruiser = new Cruiser();
        this.sub = new Sub();
        this.destroyer = new Destroyer();
    }

    /**
     * Informs the appropriate ship that it has been hit, 
     * and returns true if this sank the ship, 
     * and false if it did not
     * @param shipType shipType
     * @return
     */
    public boolean updateFleet(ShipType shipType) {
        switch (shipType) {
            case ST_AIRCRAFT_CARRIER:
                return aircraftCarrier.hit();
            case ST_BATTLESHIP:
                return battleShip.hit();
            case ST_CRUISER:
                return cruiser.hit();
            case ST_DESTROYER:
                return destroyer.hit();
            case ST_SUB:
                return sub.hit();
        } 
        return false;
    }

    /**
     * Returns true if all ships have been sunk, returns false if not.
     * @return
     */
    public boolean gameOver() {
        return aircraftCarrier.getSunk() 
            && battleShip.getSunk() 
            && cruiser.getSunk()
            && destroyer.getSunk() 
            && sub.getSunk();
    }
}
