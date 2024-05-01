import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Board {
    // INSTANCE VARIABLES
    private ArrayList<ArrayList<CellStatus>> layout;
    private Fleet fleet;
    public final static int SIZE = 10;

    // CONSTRUCTORS
    /**
     * Takes filename as a parameter. Initializes layout,
     * initially setting all cells to CellStatus.NOTHING. 
     * Gets information from file and add ships to the layout. 
     * Initializes Fleet.
     * @param fileName
     */
    public Board(String fileName) { // shipLocations
        // initialize
        layout = new ArrayList<ArrayList<CellStatus>>();
        fleet = new Fleet();

        
        // adds temp ten times
        for (int i = 0 ; i < SIZE; i++ ) {
            // create temp list of ten cellstatus nothings
            ArrayList<CellStatus> temp = new ArrayList<>();
            for (int j = 0; j < SIZE; j++) {
                temp.add(CellStatus.NOTHING);
            }
            layout.add(temp);
        }      
        // ADD SHIP LOCATIONS
        Scanner infile = null;
        try {
            infile= new Scanner(new File(fileName));
        }
        catch (IOException e) {
            System.out.println("file not found");
        }

        while (infile.hasNextLine()) {
            // checks for ship type
            String line = infile.nextLine();

            String[] lineArray = line.split(" ");

            // get the pieces
            String type = lineArray[0];
            CellStatus cellStatusType = null;
            switch (type) {
                case "C" :
                    cellStatusType = CellStatus.CRUISER;
                    break;
                case "A" :
                    cellStatusType = CellStatus.AIRCRAFT_CARRIER;
                    break;
                case "B" :
                    cellStatusType = CellStatus.BATTLESHIP;
                    break;
                case "S" :
                    cellStatusType = CellStatus.SUB;
                    break;
                case "D" :
                    cellStatusType = CellStatus.DESTROYER;
                    break;
            }

            String startLoc = lineArray[1];
            String endLoc = lineArray[2];

            // get the starting indexes #C1 #C1
            int startLocationIndexRow = (startLoc.charAt(0))-'A'; // row num #3
            int startLocationIndexCol = Integer.parseInt(startLoc.substring(1))-1; // col num #1

            // get the ending indexes #C3 #E1
            int endLocationIndexRow = (endLoc.charAt(0))-'A'; // row num #3 #5
            //parseint(substring your endLoc from 1) - 1
            int endLocationIndexCol = Integer.parseInt(endLoc.substring(1))-1;

            if (startLocationIndexRow == endLocationIndexRow) {//horizontal 
                // iterate through the row 
                // gets list row and sets it to a temp list
                for (int i = startLocationIndexCol; i <= endLocationIndexCol; i++) {
                    // sets each index to appropriate cell type
                    getLayout().get(startLocationIndexRow).set(i, cellStatusType);
                }
            }
            else if (startLocationIndexCol == endLocationIndexCol) {// vertical
                // iterate through the col
                for (int i = startLocationIndexRow; i <= endLocationIndexRow; i++) {
                    // gets list row and sets it to a temp list
                    getLayout().get(i).set(endLocationIndexCol, cellStatusType);
                }
            }
        }
    }
    // METHODS
    /**
     * Applies a move to layout. If the targeted cell does not contain a ship,
     * it is set to CellStatus.NOTHING_HIT. If it contains a ship, the cell
     * is changed from, for instance, CellStatus.SUB to CellStatus.SUB_HIT. 
     * It returns the original CellStatus of the targeted cell
     * @param m Move
     * @return Cellstatus of the of the targeted cell from move
     */
    public CellStatus applyMoveToLayout(Move m) {
        CellStatus targetedSquareStatus = layout.get(m.row()).get(m.col());
        CellStatus var = null;
        if (targetedSquareStatus == CellStatus.NOTHING) {
            layout.get(m.row()).set(m.col(), CellStatus.NOTHING_HIT);
        }
        switch (targetedSquareStatus) {
            case CRUISER:
                layout.get(m.row()).set(m.col(), CellStatus.CRUISER_HIT);
                var = CellStatus.CRUISER;
                break;
            case DESTROYER:
                layout.get(m.row()).set(m.col(), CellStatus.DESTROYER_HIT);
                var = CellStatus.DESTROYER;
                break;
            case AIRCRAFT_CARRIER:
                layout.get(m.row()).set(m.col(), CellStatus.AIRCRAFT_CARRIER_HIT);
                var = CellStatus.AIRCRAFT_CARRIER;
                break;
            case BATTLESHIP:
                layout.get(m.row()).set(m.col(), CellStatus.BATTLESHIP_HIT);
                var = CellStatus.BATTLESHIP;
                break;
            case SUB:
                layout.get(m.row()).set(m.col(), CellStatus.SUB_HIT);
                var = CellStatus.SUB;
                break;
            default:
                var = CellStatus.NOTHING;
                break;
        } 
        return var;
    }
    /**
     * moveAvailable Takes a move as a parameter and determines if 
     * the spot is available. (any CellStatus that isn’t hit or sunk)
     * @param m Move to test
     * @return boolean true if move is available, false if not
     */
    public boolean moveAvailable(Move m) {
        boolean status = true;
        CellStatus square = layout.get(m.row()).get(m.col());
        if ( (square != CellStatus.BATTLESHIP) && (square != CellStatus.CRUISER)
        && (square != CellStatus.AIRCRAFT_CARRIER) && (square != CellStatus.SUB)
        && (square != CellStatus.DESTROYER) && (square != CellStatus.NOTHING))
            {
                status = false;
            }
        return status;
    }
    /**
     * getLayout returns a reference to the layout 
     * @return layout
     */
    public ArrayList<ArrayList<CellStatus>> getLayout() {
        return layout;
    }
    /**
     * getFleet returns a reference to the Fleet object
     * @return fleet
     */
    public Fleet getFleet() {
        return fleet;
    }
    /**
     * gameOver returns true if all ships have been sunk, false otherwise.
     * @return true if all ships are sunk, false if not
     */
    public boolean gameOver() {
        return fleet.gameOver();
    }
}