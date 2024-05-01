public class Game {
    // INSTANCE VARIABLES
    private ComputerBoard computer;
    private UserBoard player;

    // CONTRUCTOR(S)
    /**
     * default Constructor Game
     * creates the two boards (file names are fixed “compFleet.txt”
     * and “userFleet.txt”, no need to prompt for them or pass in filename)
     */
    public Game() {
        computer = new ComputerBoard("compFleet.txt");
        player = new UserBoard("userFleet.txt");
    }

    // METHODS
    /**
     * method makeComputerMove
     * calls a method on the player board which makes a move against that board.
     * Returns an array of two Strings. The first is the move the computer made 
     * in user readable form. The second is either null, or, if the move 
     * resulted in a ship being sunk, a string along the lines of 
     * "You sunk my Battleship!"
     * @return
     */
    public String[] makeComputerMove() {
        return player.makeComputerMove();
    }
    /**
     * makePlayerMove Calls a method on the computer board which makes a move 
     * against that board. Returns either null, or, if the move resulted in a 
     * ship being sunk, a string along the lines of "You sunk my Battleship!".
     * @param s
     * @return
    */
    public String makePlayerMove(String strMove) {
        Move move = new Move(strMove);
        return computer.makePlayerMove(move);
    }
    /**
     * computerDefeated checks to see if the computer has been defeated.
     * Returns True if all computer ships have been sunk, false otherwise
     * @return boolean 
     */
    public boolean computerDefeated() {
        boolean gameOver = false;
        if (computer.gameOver()) {
            gameOver = true;
        }
        return gameOver;
    }
    /**
     * userDefeated checks to see if the player has been defeated. Returns True 
     * if all player ships have been sunk, false otherwise
     * @return
     */
    public boolean userDefeated() {
        boolean gameOver = false;
        if (player.gameOver()) {
            gameOver = true;
        }
        return gameOver;
    }
    /**
     * toString Returns a string representation of both boards well labelled
     * @return String of boards
     */
    public String toString() {
        String s = "";
        int count;

        // Computer display
        s += "COMPUTER\n";
        s += "     1  2  3  4  5  6  7  8  9  10\n";
        String[] compArry = computer.toString().split("  ");
        count = 65;
        s+= "  ";  
        for (int i = 0; i < compArry.length; i++) {
            //s+= i;
            // gives us the next letter every 10 cells
            if (i%11 == 0) {
                s += (char) count;
                count+=1;
                s+= "  ";
            }
            s += compArry[i];
            s += "  ";
        }

        // Player Display
        s += "\nPLAYER\n";
        s += "     1  2  3  4  5  6  7  8  9  10\n";
        String[] plyrArry = player.toString().split("  ");
        count = 65;
        s+= "  ";  
        for (int i = 0; i < plyrArry.length; i++) {
            //s+= i;
            // gives us the next letter every 10 cells
            if (i%11 == 0) {
                s += (char) count;
                count+=1;
                s+= "  ";
            }
            s += plyrArry[i];
            s += "  ";
        }
        return s;
    }
}
