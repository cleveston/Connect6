package player;

import main.Move;
import java.util.*;
import player.Players;

//The Node Class
public class Node {

    //The move
    private Move move;

    //The node`s score
    private int score;

    //The parent node
    private final Node parent;

    //Flag that show if the node is leaf
    private boolean isFirstBoard = false;

    //The children list
    private ArrayList<Node> children;

    //Define the current player
    private Players currentPlayer;

    //The current board
    private Board currentBoard;

    //The Constructor Method for the root node
    public Node(Board board, Players player) {

        //Define the current board
        this.currentBoard = board;

        //Define the current player
        this.currentPlayer = player;

        //Set the parent node
        this.parent = null;

        //Define as first board
        this.isFirstBoard = true;

        //Create the children list
        this.children = new ArrayList<>();

    }

    //The Constructor Method for the other nodes
    public Node(Board board, Players player, Move move, Node parent) {

        //Define the current board
        this.currentBoard = board;

        //Define the current player
        this.currentPlayer = player;

        //Define the new move
        this.move = move;

        //Set the parent node
        this.parent = parent;

        //Create the children list
        this.children = new ArrayList<>();

    }

    //Expand the node
    private void expand() {

        //For the first stone
        for (int line = 0; line < currentBoard.getSize(); line++) {
            for (int collunm = 0; collunm < currentBoard.getSize(); collunm++) {

                //For the second stone
                for (int line2 = 0; line2 < currentBoard.getSize(); line2++) {
                    for (int collunm2 = 0; collunm2 < currentBoard.getSize(); collunm2++) {

                        //Create a new move
                        Move newMove = new Move(line, collunm, line2, collunm2);

                        //Only add if movement is possible
                        if (!currentBoard.isIllegalMove(newMove)) {

                            //Clone the board
                            Board newBoard = (Board) currentBoard.clone();

                            Node newNode;

                            //Create new child
                            if (this.isFirstBoard) {
                                newNode = new Node(newBoard, currentPlayer, newMove, this);

                            } else {
                                newNode = new Node(newBoard, Players.switchPlayer(currentPlayer), newMove, this);
                            }

                            //Change the board
                            newNode.executeMove();

                            //If the child is already added, do not add again
                            if (!children.contains(newNode)) {

                                //Score the new node
                                newNode.scoreNode();

                                //Add new node in the children list
                                children.add(newNode);
                            }
                        }
                    }
                }
            }
        }
    }

    //Score the node
    private void scoreNode() {

        //Get the current board
        int[][] board = this.currentBoard.getBoard();

        int aiScore = 1;
        int score = 0;
        int blanks = 0;
        int k = 0;

        //Get players values
        int valueCurrentPlayer = Players.getValue(this.currentPlayer);
        int valueOppositePlayer = Players.getValue(Players.switchPlayer(this.currentPlayer));

        for (int i = currentBoard.getSize() - 1; i >= 0; --i) {
            for (int j = 0; j <= currentBoard.getSize() - 1; ++j) {

                //If the position is blank, just go on
                if (board[i][j] == 0 || board[i][j] == valueOppositePlayer) {
                    continue;
                }

                //Score board vertically
                if (i >= 5) {
                    for (k = 0; k < 6; ++k) {
                        if (board[i - k][j] == valueCurrentPlayer) {
                            aiScore++;
                        } else if (board[i - k][j] == valueOppositePlayer) {
                            aiScore = 0;
                            blanks = 0;
                            break;
                        } else {
                            blanks++;
                        }
                    }

                    if (blanks > 0) {

                        //Calculate the score
                        score += calculateScore(aiScore, blanks);
                    }

                    //Reset counters
                    aiScore = 1;
                    blanks = 0;
                }

                //Score board horizontally
                if (j >= 5) {

                    for (k = 0; k < 6; ++k) {
                        if (board[i][j - k] == valueCurrentPlayer) {
                            aiScore++;
                        } else if (board[i][j - k] == valueOppositePlayer) {
                            aiScore = 0;
                            blanks = 0;
                            break;
                        } else {
                            blanks++;
                        }
                    }

                    if (blanks > 0 || aiScore > 1) {

                        //Calculate the score
                        score += calculateScore(aiScore, blanks);

                    }
                    //Reset counters
                    aiScore = 1;
                    blanks = 0;
                }

                //Score stones up-right
                if (j <= this.currentBoard.getSize() - 6 && i >= 5) {

                    for (k = 0; k <= 5; k++) {
                        if (board[i - k][j + k] == valueCurrentPlayer) {
                            aiScore++;
                        } else if (board[i - k][j + k] == valueOppositePlayer) {
                            aiScore = 0;
                            blanks = 0;
                            break;
                        } else {
                            blanks++;
                        }
                    }

                    if (blanks > 0 || aiScore > 1) {

                        //Calculate the score
                        score += calculateScore(aiScore, blanks);
                    }

                    //Reset counters
                    aiScore = 1;
                    blanks = 0;

                }

                //Score stones up-left
                if (i >= 5 && j >= 5) {
                    for (k = 0; k < 6; ++k) {
                        if (board[i - k][j - k] == valueCurrentPlayer) {
                            aiScore++;
                        } else if (board[i - k][j - k] == valueOppositePlayer) {
                            aiScore = 0;
                            blanks = 0;
                            break;
                        } else {
                            blanks++;
                        }
                    }

                    if (blanks > 0 || aiScore > 1) {

                        //Calculate the score
                        score += calculateScore(aiScore, blanks);

                    }

                    //Reset counters
                    aiScore = 1;
                    blanks = 0;
                }
            }
        }

        //Set the node`s score
        this.score = score;
    }

    //Calculate the score with weight
    private int calculateScore(int aiScore, int moreMoves) {

        //Moves left
        int moveScore = moreMoves + 1;

        //Depending on the aiScore, define the weight
        switch (aiScore) {
            case 0:
                return 0;
            case 1:
                return 1 * moveScore;
            case 2:
                return 10 * moveScore;
            case 3:
                return 100 * moveScore;
            case 4:
                return 1000 * moveScore;
            case 5:
                return 10000 * moveScore;
            default:
                return 10000;
        }
    }

    //Execute the move
    private void executeMove() {

        //Update the current board
        this.currentBoard.update(this.move, this.currentPlayer);
    }

    //Get the current player
    public Players getCurrentPlayer() {
        return currentPlayer;
    }

    //Get node`s score
    public int getScore() {
        return score;
    }

    //Get the parent node
    public Node getParent() {
        return this.parent;
    }

    //Get the current board
    public Board getBoard() {
        return this.currentBoard;
    }

    //Get the children
    public ArrayList<Node> getChildren() {

        //If the children list is empty
        if (this.children.isEmpty()) {

            //Expand the children
            this.expand();
        }

        //Return the children
        return this.children;
    }

    //Return the move
    public Move getMove() {
        return this.move;
    }

    @Override
    public boolean equals(Object node) {

        Node n = (Node) node;

        //Get the foreign board
        int[][] nBoard = n.currentBoard.getBoard();

        //Get my board
        int[][] myBoard = this.currentBoard.getBoard();

        int index = 0;

        //Compare the lines
        for (int[] i : nBoard) {

            //If they are not equal, return false
            if (!Arrays.equals(myBoard[index], i)) {
                return false;
            }

            index++;

        }

        return true;

    }

}
