/*
 * The UserBoard maintains a list of all possible moves. 
 * Initially, it will be all locations on the Board. 
 * When the computer takes a turn, it randomly selects
 * an item from this list and removes it from the list.
 */
import java.util.ArrayList;
import java.util.Random;

public class UserBoard extends Board{
    // INSTANCE VARIABLES
    private ArrayList<Move> moves;
    private Random rand;

    // CONSTRUCTOR
    /**
     * Passes the filename on to the Board constructor. 
     * Initialize the Random object and the ArrayList of all possible Moves.
     * @param fileName
     */
    public UserBoard(String fileName) {
        super(fileName);
        rand = new Random(); 
        moves = new ArrayList<Move>();
        // add to array list all possible moves
        for (int i = 0; i < 10; i++) {
            for (int j = 0;j < 10; j++) {
                moves.add(new Move(i,j));
            }
        }
    }

    /**
     * Computer move against UserBoard. Selects and makes a move AGAINST 
     * this board. Returns an array of two Strings. The first is the move the 
     * computer made in user readable form. The second is either null, or, if
     * the move resulted in a ship being sunk, a string along the lines of 
     * "You sunk my Battleship!"
     * @return
     */
    public String [] makeComputerMove() {
        // return array of two strings
        String[] returnArray = new String[2];
        // get random col and location from list
        Move compMove = moves.get(rand.nextInt(moves.size()));
        moves.remove(compMove);

        // add valid location to string array
        returnArray[0] = compMove.toString();

        // apply move
        CellStatus cellShot = super.applyMoveToLayout(compMove);
        String message = null;
        //switch case or if/else statements that determine what shiptype the cellShot is
        switch (cellShot) {
            //if updateFleet is true, set your message to "you sunk my ..."
            // also updates cellstatus to _SUNK if its _HIT
            case BATTLESHIP:
                if (super.getFleet().updateFleet(ShipType.ST_BATTLESHIP)) {
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
        returnArray[1] = message;
        return returnArray;
    }
    /**
     * toString Returns a String representation of the ComputerBoard,
     * displaying the second character of the String returned by the 
     * toString method overridden in CellStatus
     */
    public String toString() {
        String s = "";
        for (int i = 0; i <= 9; i++) {
            ArrayList<CellStatus> tempList = super.getLayout().get(i);
            for (int j = 0; j <= 9; j++) {
                s += String.format("%c  ",
                tempList.get(j).toString().charAt(1));
            }
            s += "\n  ";
        }
        return s;
    }    
}
