import java.util.ArrayList;
public class ComputerBoard extends Board{
    /**
     * Constructor Passes the filename on to the Board constructor
     * @param fileName
     */
    public ComputerBoard(String fileName) {
        super(fileName);
    }

    /**
     * makePlayerMove Takes a move and makes it AGAINST this board. 
     * Takes in move to be applied. Returns either null, or, if the move
     * sank a ship, a String along the lines of 
     * "You sank My Battleship!" if a ship was sunk, update the
     * layout to change _HIT values to _SUNK values
     * @param m
     * @return
     */
    public String makePlayerMove(Move m) {
        if (super.moveAvailable(m) == false) { // cell is hit or sunk already
            return null;// cell is not available
        }
        // apply the move to layout, either is a miss or hit, could sink
        String message = null;
        // cellshot is either NOTHING or SHIPTYPE_HIT
        CellStatus cellShot = super.applyMoveToLayout(m); 
        
        //switch case or if/else statements that determine what shiptype the cellShot is
        switch (cellShot) {
            //if updateFleet is true, set your message to "you sunk my ..."
            case BATTLESHIP:
                //if update fleet returns true then its sunk so
                // set message to you sank my battle ship and set _HIT to _SUNK
                if (getFleet().updateFleet(ShipType.ST_BATTLESHIP)) {
                    for (int i = 0; i <= 9; i++) {
                        ArrayList<CellStatus> tempList = super.getLayout().get(i);
                        for (int j = 0; j <= 9; j++) {
                            if (tempList.get(j).equals(CellStatus.BATTLESHIP_HIT)){
                                tempList.set(j, CellStatus.BATTLESHIP_SUNK);
                            }
                        }
                        super.getLayout().set(i, tempList);
                    }  
                    message = "You sank My Battleship!";
                }
                break;
            case AIRCRAFT_CARRIER:
                if (getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER)) {
                    for (int i = 0; i <= 9; i++) {
                        ArrayList<CellStatus> tempList = super.getLayout().get(i);
                        for (int j = 0; j <= 9; j++) {
                            if (tempList.get(j).equals(CellStatus.AIRCRAFT_CARRIER_HIT)){
                                tempList.set(j, CellStatus.AIRCRAFT_CARRIER_SUNK);
                            }
                        }
                        super.getLayout().set(i, tempList);
                    } 
                    message = "You sank My Aircraft Carrier!";
                }
                break;
            case DESTROYER:
                if (getFleet().updateFleet(ShipType.ST_DESTROYER)) {
                    for (int i = 0; i <= 9; i++) {
                        ArrayList<CellStatus> tempList = super.getLayout().get(i);
                        for (int j = 0; j <= 9; j++) {
                            if (tempList.get(j).equals(CellStatus.DESTROYER_HIT)){
                                tempList.set(j, CellStatus.DESTROYER_SUNK);
                            }
                        }
                        super.getLayout().set(i, tempList);
                    } 
                    message = "You sank My Destroyer!";
                }
                break;
            case CRUISER:
                if (getFleet().updateFleet(ShipType.ST_CRUISER)) {
                    for (int i = 0; i <= 9; i++) {
                        ArrayList<CellStatus> tempList = super.getLayout().get(i);
                        for (int j = 0; j <= 9; j++) {
                            if (tempList.get(j).equals(CellStatus.CRUISER_HIT)){
                                tempList.set(j, CellStatus.CRUISER_SUNK);
                            }
                        }
                        super.getLayout().set(i, tempList);
                    }  
                    message = "You sank My Cruiser!";
                }
                break;
            case SUB:
                if (getFleet().updateFleet(ShipType.ST_SUB)) {
                    for (int i = 0; i <= 9; i++) {
                        ArrayList<CellStatus> tempList = super.getLayout().get(i);
                        for (int j = 0; j <= 9; j++) {
                            if (tempList.get(j).equals(CellStatus.SUB_HIT)){
                                tempList.set(j, CellStatus.SUB_SUNK);
                            }
                        }
                        super.getLayout().set(i, tempList);
                    }
                    message = "You sank My Sub!";
                }
                break;
            default:
                break;
        }
        return message;
    }

    /**
     * toString Returns a String representation of the ComputerBoard, 
     * displaying the first character of the String returned by the toString
     * method overridden in CellStatus
     */
    public String toString() {
        String s = "";
        for (int i = 0; i <= 9; i++) {
            ArrayList<CellStatus> tempList = super.getLayout().get(i);
            for (int j = 0; j <= 9; j++) {
                s += String.format("%c  ",
                 tempList.get(j).toString().charAt(0));
            }
            s += "\n  ";
        }
        return s;
    }
}
