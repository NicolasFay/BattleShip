import java.util.Random;
import java.util.Scanner;
public class BattleshipDriver {
    public static void main(String[] args) {
        Game game = null;
        // file not found handling
        try {
            game = new Game();
        } catch (Exception FileNotFoundException) {
            System.exit(1);
        }
        Random rand = new Random();

        // begin output
        System.out.println("Welcom to BattleShip!\n");
        System.out.println(game);

        // get random starter with coin toss
        int coinToss = rand.nextInt(2); // will be 0 or 1
        boolean playersTurn = false;
        if (coinToss == 0) {
            playersTurn = true;
            System.out.println("You won the coin toss and get to go first.");
        } 
        else if (coinToss == 1) {
            System.out.println("The computer won the coin toss" + 
                                "and gets to go first");
        }

        Scanner input = new Scanner(System.in);
        String message;
        while (!game.computerDefeated() && !game.userDefeated()) {
            // Players Turn
            if (playersTurn) {
                boolean done = false;
                String userMove = "";
                while (!done) {
                    System.out.print("Your turn: ");
                    userMove = input.nextLine();
                    // VALIDATE MOVE
                    // if its out of bounds for move try again
                    // if its duplicate then take no action
                }
            
                message = game.makePlayerMove(userMove);
                if (message != null) {
                    System.out.println(message);
                }
            }
            // Computers Turn
            else if (!playersTurn) {
                System.out.println("Computer's turn. Press any key to continue.\n");
                String cont = input.nextLine();

                // String of move location and message for sunk ships
                String[] results = game.makeComputerMove();
                // displays the computers move
                System.out.printf("Computer Chose: %s\n",results[0]);
                // if message isnt null display it
                if (results[1] != null) {
                    System.out.printf("Computers message: %s\n", results[1]);
                }
            }
            // display board
            System.out.println("\n" + game);

            // switch whose turn it is
            if (playersTurn == true) {
                playersTurn = false;
            } 
            else if (playersTurn == false) {
                playersTurn = true;
            }
        }
        // end of game
        input.close();

        if (game.computerDefeated()) {
            System.out.println("Game Over\nYou won!");
        }
        else if (game.userDefeated()) {
            System.out.println("Game Over\nComputer won!");
        }
    }
}
