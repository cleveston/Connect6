package main;

import java.util.*;
import player.Players;

//The Connect6 Driver Class
public class Connect6Driver {

    //Default time per move
    private static final int MAX_TIME_PER_MOVE = 5;

    //The boardSize
    private static int boardSize;

    //The Game Board
    private static Game gameBoard;

    //The player
    private static BlackPlayer blackPlayer;
    private static WhitePlayer whitePlayer;

    //Console Scanner
    private static Scanner sc = new Scanner(System.in);

    //The default turn
    private static Players currentTurn = Players.BLACK;

    //The Main Method
    public static void main(String[] args) {

        //Try to construct the board and Players
        try {

            //Set the boardSize
            boardSize = Integer.parseInt(args[0]);

            //Create the gameBoard
            gameBoard = new Game(boardSize);

            //Read the player type
            if (args[1].equals("-c")) {

                //Create the black player
                blackPlayer = new BlackPlayer(args[2], boardSize, MAX_TIME_PER_MOVE);
            }

            if (args[3].equals("-c")) {

                //Create the white player
                whitePlayer = new WhitePlayer(args[4], boardSize, MAX_TIME_PER_MOVE);
            }

        } catch (Exception exception) {

            System.out.println("Arguments are not correct");

            System.exit(0);
        }

        //The current move
        Move currentMove;

        //Print the initial board
        gameBoard.printBoard();

        //Repeat until one movement is wrong, or someone wins
        while (true) {

            //Get the move from human or AI player
            if (currentTurn == Players.BLACK) {

                if (blackPlayer != null) {

                    //Get the black player move
                    currentMove = blackPlayer.getMove();
                } else {

                    //Get the human move
                    currentMove = getHumanMove(args[2]);
                }
            } else if (whitePlayer != null) {

                //Get the white player move
                currentMove = whitePlayer.getMove();
            } else {

                //Get the human move
                currentMove = getHumanMove(args[4]);
            }

            //Check the movement
            if (gameBoard.isIllegalMove(currentMove)) {

                System.out.println("Illegal move is made");
                break;
            } else {

                //Update the game board with the new movement
                gameBoard.update(currentMove, currentTurn.toString());

                //Print the board
                gameBoard.printBoard();

                System.out.println("--------------------------------------");
                System.out.println("Press enter for the next turn");
                sc.nextLine();

                //Get the game result
                int result = gameBoard.getResult();

                //Check if the game is over
                if (result == 1) {
                    System.out.println("BLACK WON");
                    break;
                } else if (result == 2) {
                    System.out.println("WHITE WON");
                    break;
                } else if (result == 0) {
                    System.out.println("DRAW");
                    break;
                } else if (currentTurn == Players.BLACK) {

                    //If player is not null, update it
                    if (whitePlayer != null) {
                        //Update the opponent
                        whitePlayer.update(currentMove);
                    }

                    //Change the turn
                    currentTurn = Players.WHITE;
                } else {

                    //If player is not null, update it
                    if (blackPlayer != null) {
                        //Update the opponent
                        blackPlayer.update(currentMove);
                    }

                    //Change the turn
                    currentTurn = Players.BLACK;
                }

            }

        }
    }

    //Get a human movement
    private static Move getHumanMove(String name) {

        //Repeat until the user writes the position correctly
        while (true) {

            try {

                System.out.print("Hello " + name + ",\nprovide x1 y1 x2 y2 for choosing (x1,y1) and (x2,y2): ");

                //Read the position
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();

                //Return the new Move
                return new Move(x1, y1, x2, y2);

            } catch (Exception exception) {

                //Clear the buffer
                sc.nextLine();
            }

        }
    }

}
