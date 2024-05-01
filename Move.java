public class Move {
    // INSTANCE VARIABLES
    private int row;
    private int col;

    // CONSTRUCTORS
    /**
     * default constructor takes two ints for row and col
     * @param row
     * @param col
     */
    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Creates a move object from a String consisting of a letter and a number
     * @param coords
     */
    public Move(String coords) {
        this.row = (coords.toUpperCase().charAt(0)) - 'A' ;
        this.col = Integer.parseInt(coords.substring(1)) - 1;
    }

    // METHODS
    /**
     * getter for row 
     * @return int row
     */
    public int row() {
        return row;
    }

    /**
     * getter for col
     * @return int col
     */
    public int col() {
        return col;
    }

    @Override
    /**
     * method toString returns string of move class
     */
    public String toString() {
        
        return String.format("%c%d",row+65, col);
    }
}
