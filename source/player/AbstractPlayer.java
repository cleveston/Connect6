package player;

import java.util.ArrayList;
import main.Move;

//The Abstract Class Player
public abstract class AbstractPlayer {

    //Define the current player
    protected Players player;

    //The private variables
    protected String name;
    protected int boarSize;
    protected int maxTimePerMove;

    //The current board
    protected Board board;

    //Maximum depth
    protected int MAX_DEPTH = 1;

    //The MiniMax Algorithm
    protected MiniMax minimax;

    //Update the competitor move
    public void update(Move lastMove) {

        //Update the internal board, with the opposite player
        this.board.update(lastMove, Players.switchPlayer(player));

    }

    //Get the move
    public Move getMove() {

        //Define the best move
        Move bestMove = null;

        //Define the initial best score
        int bestScore = Integer.MIN_VALUE;

        //Create the root node with the current board 
        Node root = new Node(this.board, this.player);

        //Get the children
        ArrayList<Node> children = root.getChildren();

        //For each root`s node children
        for (Node child : children) {

            //Change the depht based on the size of the list
            if (children.size() < 10) {
                MAX_DEPTH = 3;
            } else if (children.size() < 20) {
                MAX_DEPTH = 2;
            }

            //Get the currentScore
            int currentScore = minimax.calc(child, MAX_DEPTH, bestScore, Integer.MAX_VALUE);

            //If currentScore is better than the bestScore, update currentScore
            if (currentScore > bestScore || bestMove == null) {

                //Update the bestMove and the bestScore
                bestMove = child.getMove();
                bestScore = currentScore;

            }
        }

        //Update my own board
        board.update(bestMove, this.player);

        //Return the best move
        return bestMove;
    }

    //Print some boards - DEBUG MODE
    public void print() {

        //Create the root node with the current board 
        Node root = new Node(this.board, this.player);

        root = root;

        int index = 0;

        // System.out.println("SIZE: " + root.getChildren().size());
        //For each root`s node children
        for (Node child : root.getChildren()) {

            child.getBoard().print();
            System.out.println("INDEX: " + index);
            System.out.println("SCORE: " + child.getScore());

            index++;

        }
    }

}
